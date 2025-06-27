package com.jobportal.mapper;

import com.jobportal.entity.Job;
import com.jobportal.entity.JobDto;
import org.springframework.stereotype.Service;

@Service
public class JobMapper {

    public JobDto toJobDto(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .jobTitle(job.getJobTitle())
                .company(job.getCompany())
                .applicants(job.getApplicants())
                .about(job.getAbout())
                .experience(job.getExperience())
                .jobType(job.getJobType())
                .location(job.getLocation())
                .packageOffered(job.getPackageOffered())
                .postTime(job.getPostTime())
                .description(job.getDescription())
                .skillRequired(job.getSkillRequired())
                .jobStatus(job.getJobStatus())
                .build();
    }

    public Job toJob(JobDto jobDto) {
        return Job.builder()
                .id(jobDto.getId())
                .jobTitle(jobDto.getJobTitle())
                .company(jobDto.getCompany())
                .applicants(jobDto.getApplicants())
                .about(jobDto.getAbout())
                .experience(jobDto.getExperience())
                .jobType(jobDto.getJobType())
                .location(jobDto.getLocation())
                .packageOffered(jobDto.getPackageOffered())
                .postTime(jobDto.getPostTime())
                .description(jobDto.getDescription())
                .skillRequired(jobDto.getSkillRequired())
                .jobStatus(jobDto.getJobStatus())
                .build();
    }
}

