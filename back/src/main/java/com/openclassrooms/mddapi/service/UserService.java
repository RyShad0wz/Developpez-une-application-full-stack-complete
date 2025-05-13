package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
}
