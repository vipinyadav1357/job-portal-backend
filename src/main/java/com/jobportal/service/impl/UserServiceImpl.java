package com.jobportal.service.impl;

import com.jobportal.dtos.LoginDto;
import com.jobportal.dtos.UserDto;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.mapper.UserMapper;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;
import com.jobportal.utils.SequenceUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto dto) throws JobPortalException {
        Optional<User> user1 = userRepository.findByEmail(dto.getEmail());
        if (user1.isPresent())
            throw new JobPortalException("User Already Registered");
        dto.setId(SequenceUtilities.getNextSequence("users"));
        User user = userMapper.toUser(dto);
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public LoginDto loginUser(LoginDto dto) throws JobPortalException {
        var user1 = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new JobPortalException(dto.getEmail() + " is not found "));
        return passwordEncoder.matches(dto.getPassword(), user1.getPassword()) ? dto : new LoginDto();
    }
}
