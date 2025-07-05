package com.jobportal.mapper;

import com.jobportal.dtos.JobDto;
import com.jobportal.entity.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobMapper {
private final ApplicantMapper applicantMapper;
    public JobDto toJobDto(Job job) {
        if (job == null) {
            return null;
        }
        return JobDto.builder()
                .id(job.getId())
                .postedBy(job.getPostedBy())
                .jobTitle(job.getJobTitle())
                .company(job.getCompany())
                .applicants(job.getApplicants()!=null ? job.getApplicants().stream().map(applicantMapper::toApplicantDto).toList(): List.of())
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
        if (jobDto == null) {
            return null;
        }
        return Job.builder()
                .id(jobDto.getId())
                .postedBy(jobDto.getPostedBy())
                .jobTitle(jobDto.getJobTitle())
                .company(jobDto.getCompany())
                .applicants(jobDto.getApplicants()!=null?jobDto.getApplicants().stream().map(applicantMapper::toApplicant).toList():List.of())
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

