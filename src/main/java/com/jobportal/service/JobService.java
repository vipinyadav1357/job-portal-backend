package com.jobportal.service;

import com.jobportal.entity.JobDto;
import jakarta.validation.Valid;

public interface JobService {
    JobDto postJob(@Valid JobDto jobDto);
}
