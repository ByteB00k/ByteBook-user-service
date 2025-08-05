package com.bytebook.userservice.service;

import com.bytebook.userservice.dto.TelegramKafkaDto;
import com.bytebook.userservice.model.user.Language;
import com.bytebook.userservice.model.user.Role;
import com.bytebook.userservice.model.user.Status;
import com.bytebook.userservice.model.user.User;
import com.bytebook.userservice.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @KafkaListener(topics = "orchestratorToUser", groupId = "user-consumer-group")
    public void consume(String message) throws JsonProcessingException {
        boolean isUpdated = false;
        TelegramKafkaDto telegramKafkaDto = objectMapper.readValue(message, TelegramKafkaDto.class);
        TelegramKafkaDto.TelegramUser from = telegramKafkaDto.getMessage().getFrom();

        Optional<User> optionalUser = userRepository.findByUsername(from.getUsername());

        User user = optionalUser.orElse(User.builder().guid(UUID.randomUUID())
                .username(from.getUsername())
                .role(Role.USER)
                .status(Status.ACTIVE)
                .first_name(from.getFirst_name())
                .language(Language.fromString(from.getLanguage_code()))
                .updatedAt(Instant.now())
                .build());

        if (optionalUser.isPresent() && user.getStatus() != Status.BANNED) {
            user.setFirst_name(from.getFirst_name());
            user.setLanguage(Language.valueOf(from.getLanguage_code()));
            user.setUpdatedAt(Instant.now());
            isUpdated = true;
        }

        if (isUpdated || optionalUser.isEmpty()) {
            userRepository.save(user);
            log.info("User saved: {}", user);
        } else {
           log.warn("User wasn't updated: {}", user);
        }
    }
}
