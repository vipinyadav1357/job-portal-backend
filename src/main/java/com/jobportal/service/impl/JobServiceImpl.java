package com.jobportal.service.impl;

import com.jobportal.dtos.ApplicantDto;
import com.jobportal.dtos.Application;
import com.jobportal.dtos.JobDto;
import com.jobportal.entity.Applicant;
import com.jobportal.entity.Job;
import com.jobportal.enums.ApplicationStatus;
import com.jobportal.enums.JobStatus;
import com.jobportal.exception.JobPortalException;
import com.jobportal.mapper.ApplicantMapper;
import com.jobportal.mapper.JobMapper;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.JobService;
import com.jobportal.utils.SequenceUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final ApplicantMapper applicantMapper;
    private final UserRepository userRepository;

    @Override
    public JobDto postJob(JobDto jobDto) {
        if(jobDto.getId()==0){
        jobDto.setId(SequenceUtilities.getNextSequence("jobs"));
        jobDto.setPostTime(LocalDateTime.now());
        }else{
         Job job=jobRepository.findById(jobDto.getId()).orElseThrow(() -> new JobPortalException("job not found"));
            if(job.getJobStatus().equals(JobStatus.CLOSED)||job.getJobStatus().equals(JobStatus.DRAFT))
                jobDto.setPostTime(LocalDateTime.now());
        }
        return jobMapper.toJobDto(jobRepository.save(jobMapper.toJob(jobDto)));
    }

    @Override
    public List<JobDto> getAll() {
        return jobRepository.findAll().stream().map(jobMapper::toJobDto).toList();
    }

    @Override
    public JobDto getJob(Long jobId) {
        var job = jobRepository.findById(jobId).orElseThrow(() -> new JobPortalException("job not found"));
        return jobMapper.toJobDto(job);
    }

    @Override
    public void applyJob(ApplicantDto applicantDto, Long jobId) {
        var job = jobRepository.findById(jobId).orElseThrow(() -> new JobPortalException("ha ha ha ha ha job not found"));

        List<Applicant> applicants = job.getApplicants();

        if (applicants == null)
            applicants = new ArrayList<>();


        if (!applicants.stream().filter(applicantsData -> Objects.equals(applicantsData.getEmail(), applicantDto.getEmail())).toList().isEmpty()) {
            throw new JobPortalException("already applied");
        } else {
            applicantDto.setApplicationStatus(ApplicationStatus.APPLIED);
            applicantDto.setApplicantId(1L);
            applicantDto.setAppliedTime(LocalDateTime.now());
            Applicant applicant = applicantMapper.toApplicant(applicantDto);
            applicants.add(applicant);
            job.setApplicants(applicants);
            jobRepository.save(job);
        }
    }

    @Override
    public List<JobDto> getAllJobPostedBy(Long id) {
        userRepository.findById(id).orElseThrow(() -> new JobPortalException(("user not found")));
        Job job = new Job();
        job.setPostedBy(id);
        Example<Job> example = Example.of(job);
        return jobRepository.findAll(example).stream().map(jobMapper::toJobDto).toList();
    }

    @Override
    public void changeApplicantStatus(Application application) throws JobPortalException {
        Job job = new Job();
        job.setPostedBy(application.getUserId());
        job.setId(application.getJobId());
        Example<Job> example = Example.of(job);
        job = jobRepository.findOne(example).orElseThrow();
        List<Applicant> applicantList = job.getApplicants();
        applicantList = applicantList
                .stream()
                .map((applicant) -> {
                    if (application.getApplicantId().equals(applicant.getApplicantId())) {
                        applicant.setApplicationStatus(application.getApplicationStatus());
                    }
                    if (application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) {
                        applicant.setInterViewTime(application.getInterViewTime());
                    }
                    return applicant;
                }).toList();
        job.setApplicants(applicantList);
        jobRepository.save(job);
    }
}
