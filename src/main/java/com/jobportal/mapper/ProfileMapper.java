package com.jobportal.mapper;

import com.jobportal.dtos.ProfileDto;
import com.jobportal.entity.Profile;

public class ProfileMapper {

    public ProfileDto toProfileDto(Profile profile){
        return ProfileDto
                .builder()
                .id(profile.getId())
                .email(profile.getEmail())
                .jobTitle(profile.getJobTitle())
                .company(profile.getCompany())
                .location(profile.getLocation())
                .about(profile.getAbout()).
                skills(profile.getSkills())
                .experience(profile.getExperience())
                .certifications(profile.getCertifications())
        .build();
    }
    public Profile toProfile(ProfileDto profileDto){
        return Profile
                .builder()
                .id(profileDto.getId())
                .email(profileDto.getEmail())
                .jobTitle(profileDto.getJobTitle())
                .company(profileDto.getCompany())
                .location(profileDto.getLocation())
                .about(profileDto.getAbout())
                .skills(profileDto.getSkills())
                .experience(profileDto.getExperience())
                .certifications(profileDto.getCertifications())
                .build();
    }
}
