package com.jobportal.service;

import com.jobportal.dtos.ProfileDto;
import com.jobportal.exception.JobPortalException;

public interface ProfileService {
     Long createProfile(String email) throws JobPortalException;
     ProfileDto getProfile(Long id) throws JobPortalException;
     ProfileDto updateProfile(ProfileDto dto) throws JobPortalException;
}
