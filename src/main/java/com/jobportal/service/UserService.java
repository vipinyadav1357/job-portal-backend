package com.jobportal.service;

import com.jobportal.dtos.UserDto;
import com.jobportal.exception.JobPortalException;

public interface UserService {
    public UserDto registerUser(UserDto dto) throws JobPortalException;
}
