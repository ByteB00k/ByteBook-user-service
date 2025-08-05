package com.bytebook.userservice.dto;

import com.bytebook.userservice.model.user.Language;
import com.bytebook.userservice.model.user.Role;
import com.bytebook.userservice.model.user.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String guid;

    private String username;

    private Role role;

    private Status status;

    private String email;

    private String firstName;

    private Language language;

    private Integer timezone;

    private Instant createdAt;

    private Instant updatedAt;
}
