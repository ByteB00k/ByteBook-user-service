package com.bytebook.userservice.service;

import com.bytebook.userservice.dto.UserDto;
import com.bytebook.userservice.exceptions.UserNotFoundException;
import com.bytebook.userservice.model.user.User;
import com.bytebook.userservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.bytebook.userservice.TestUtils.getValidUser;
import static com.bytebook.userservice.TestUtils.getValidUserDto;
import static com.bytebook.userservice.TestUtils.userName;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = getValidUser();
        userDto = getValidUserDto();
    }

    @Mock
    private UserRepository userRepository;
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserByUsername() {
        when(userRepository.findByUsername(userName))
                .thenReturn(Optional.ofNullable(user));
        when(objectMapper.convertValue(user, UserDto.class))
                .thenReturn(userDto);
        UserDto userByUsername = userService.getUserByUsername(userName);
        assertNotNull(userByUsername);
        assertEquals(userDto, userByUsername);
    }

    @Test
    void getUserByUsernameNotFound() {
        when(userRepository.findByUsername("wrongUsername"))
                .thenReturn(Optional.empty());
        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class,
                () -> userService.getUserByUsername("wrongUsername"));
        assertEquals("User not found with username: wrongUsername", userNotFoundException.getMessage());
    }
}
