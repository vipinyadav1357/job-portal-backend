package com.jobportal.service;

import com.jobportal.dtos.ApplicantDto;
import com.jobportal.dtos.Application;
import com.jobportal.dtos.JobDto;
import com.jobportal.exception.JobPortalException;
import jakarta.validation.Valid;

import java.util.List;

public interface JobService {
    JobDto postJob(@Valid JobDto jobDto);

    List<JobDto> getAll();

    JobDto getJob(Long jobId);

    void applyJob(@Valid ApplicantDto applicantDto, Long jobId);

    List<JobDto> getAllJobPostedBy(Long id);

    void changeApplicantStatus(Application applicantion) throws JobPortalException;
}
