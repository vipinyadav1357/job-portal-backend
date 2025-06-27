package com.jobportal.service.impl;

import com.jobportal.entity.JobDto;
import com.jobportal.exception.JobPortalException;
import com.jobportal.mapper.JobMapper;
import com.jobportal.repository.JobRepository;
import com.jobportal.service.JobService;
import com.jobportal.utils.SequenceUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public JobDto postJob(JobDto jobDto) {
        jobDto.setId(SequenceUtilities.getNextSequence("jobs"));
        jobDto.setPostTime(LocalDateTime.now());
        return jobMapper.toJobDto(jobRepository.save(jobMapper.toJob(jobDto)));
    }

    @Override
    public List<JobDto> getAll() {
        return jobRepository.findAll().stream().map(jobMapper::toJobDto).toList();
    }

    @Override
    public JobDto getJob(Long jobId) {
        var job=jobRepository.findById(jobId).orElseThrow(()-> new JobPortalException("job not found"));
        return jobMapper.toJobDto(job);
    }
}
