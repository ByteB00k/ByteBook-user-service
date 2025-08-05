package com.bytebook.userservice.service;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.bytebook.userservice.dto.TelegramKafkaDto;
import com.bytebook.userservice.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.bytebook.userservice.TestUtils.getValidTelegramKafkaDto;
import static com.bytebook.userservice.TestUtils.getValidTelegramMessage;
import static com.bytebook.userservice.TestUtils.getValidUser;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserKafkaConsumerTest {
    private String telegramMessage;
    private TelegramKafkaDto telegramKafkaDto;

    @BeforeEach
    void setup() {
        telegramMessage = getValidTelegramMessage();
        telegramKafkaDto = getValidTelegramKafkaDto();
    }

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserKafkaConsumer userKafkaConsumer;

    @Test
    void receiveValidMessage() throws JsonProcessingException {
        when(objectMapper.readValue(telegramMessage, TelegramKafkaDto.class))
                .thenReturn(telegramKafkaDto);
        when(userRepository.findByUsername(telegramKafkaDto.getMessage().getFrom().getUsername()))
                .thenReturn(Optional.ofNullable(getValidUser()));

        Logger logger = (Logger) LoggerFactory.getLogger(UserKafkaConsumer.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        userKafkaConsumer.consume(telegramMessage);

        boolean isSaved = listAppender.list.stream()
                .anyMatch(e -> e.getFormattedMessage().contains("User saved"));
        assertTrue(isSaved);
    }
}