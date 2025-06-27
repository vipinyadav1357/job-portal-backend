package com.jobportal.service;

import com.jobportal.entity.JobDto;
import jakarta.validation.Valid;

import java.util.List;

public interface JobService {
    JobDto postJob(@Valid JobDto jobDto);

    List<JobDto> getAll();

    JobDto getJob(Long jobId);
}
