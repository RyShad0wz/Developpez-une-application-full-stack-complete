package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getUsername(), u.getEmail());
    }

    private User toEntity(UserDto dto) {
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword()); // ensure password encoded
        return u;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User saved = userRepository.save(toEntity(userDto));
        return toDto(saved);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
}
