package com.bytebook.userservice.service;

import com.bytebook.userservice.dto.UserDto;
import com.bytebook.userservice.exceptions.UserNotFoundException;
import com.bytebook.userservice.model.user.User;
import com.bytebook.userservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return objectMapper.convertValue(user, UserDto.class);
    }
}
