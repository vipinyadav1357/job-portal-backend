package com.jobportal.service.impl;

import com.jobportal.dtos.ProfileDto;
import com.jobportal.entity.Profile;
import com.jobportal.exception.JobPortalException;
import com.jobportal.mapper.ProfileMapper;
import com.jobportal.repository.ProfileRepository;
import com.jobportal.service.ProfileService;
import com.jobportal.utils.SequenceUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public Long createProfile(String email) throws JobPortalException {
        Profile profile = new Profile();
        profile.setId(SequenceUtilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExperience(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        return profileRepository.save(profile).getId();
    }

    @Override
    public ProfileDto getProfile(Long id) throws JobPortalException {
       Profile profile= profileRepository.findById(id).orElseThrow(()-> new JobPortalException("profile not found"));
        return profileMapper.toProfileDto(profile);
    }

    @Override
    public ProfileDto updateProfile(ProfileDto dto) throws JobPortalException {
         profileRepository.findById(dto.getId()).orElseThrow(()-> new JobPortalException("profile not found"));
        return profileMapper.toProfileDto(profileRepository.save(profileMapper.toProfile(dto)));
    }
}
