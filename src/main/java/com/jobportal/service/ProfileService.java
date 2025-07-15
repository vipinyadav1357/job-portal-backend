package com.jobportal.service;

import com.jobportal.dtos.ProfileDto;
import com.jobportal.exception.JobPortalException;

import java.util.List;

public interface ProfileService {
     Long createProfile(String email) throws JobPortalException;
     ProfileDto getProfile(Long id) throws JobPortalException;
     ProfileDto updateProfile(ProfileDto dto) throws JobPortalException;
    List<ProfileDto> getAllProfile()throws JobPortalException;;
}
