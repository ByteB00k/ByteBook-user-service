package com.bytebook.userservice.service;

import com.bytebook.userservice.dto.UserDto;

public interface UserService {
    UserDto getUserByUsername(String username);

}
