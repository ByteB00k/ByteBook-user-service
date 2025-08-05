package com.bytebook.userservice.controller;

import com.bytebook.userservice.dto.UserDto;
import com.bytebook.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management APIs")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get user by userName")
    @GetMapping("/{userName}")
    public ResponseEntity<UserDto> getUserByUsername(String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
}
