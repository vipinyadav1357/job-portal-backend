package com.jobportal.service.impl;

import com.jobportal.dtos.UserDto;
import com.jobportal.entity.User;
import com.jobportal.mapper.UserMapper;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private  final UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto dto) {
        User user=userMapper.toUser(dto);
        user=userRepository.save(user);
        System.out.println(dto.getName()+"you have registered on our portal");
        return userMapper.toUserDto(user);
    }
}
