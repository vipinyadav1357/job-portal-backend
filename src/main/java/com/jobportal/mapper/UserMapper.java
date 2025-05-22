package com.jobportal.mapper;

import com.jobportal.dtos.UserDto;
import com.jobportal.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User toUser(UserDto dto){
        return User.builder().id(dto.getId()).name(dto.getName()).email(dto.getEmail()).password(dto.getPassword()).accountType(dto.getAccountType()).build();
    }
    public UserDto toUserDto(User user){
        return UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).accountType(user.getAccountType()).build();
    }
}
