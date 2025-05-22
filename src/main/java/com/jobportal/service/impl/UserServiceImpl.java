package com.jobportal.service.impl;

import com.jobportal.dtos.UserDto;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.mapper.UserMapper;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;
import com.jobportal.utils.SequenceUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private  final UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto dto) throws JobPortalException {
        dto.setId(SequenceUtilities.getNextSequence("users"));
        User user=userMapper.toUser(dto);
        return userMapper.toUserDto(userRepository.save(user));
    }
}
