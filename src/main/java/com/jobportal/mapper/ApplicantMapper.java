package com.jobportal.mapper;

import com.jobportal.dtos.ApplicantDto;
import com.jobportal.entity.Applicant;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ApplicantMapper {
    public ApplicantDto toApplicantDto(Applicant applicant) {
        if (applicant == null) {
            return null;
        }

        return ApplicantDto.builder()
                .applicantId(applicant.getApplicantId())
                .name(applicant.getName())
                .email(applicant.getEmail())
                .contactNumber(applicant.getContactNumber())
                .portfolioLink(applicant.getPortfolioLink())
                .resume(applicant.getResume()!=null? Base64.getEncoder().encodeToString(applicant.getResume()):null)
                .coverLetter(applicant.getCoverLetter())
                .appliedTime(applicant.getAppliedTime())
                .interViewTime(applicant.getInterViewTime())
                .applicationStatus(applicant.getApplicationStatus())
                .build();
    }

    public Applicant toApplicant(ApplicantDto applicantDto) {
        if (applicantDto == null) {
            return null;
        }

        return Applicant.builder()
                .applicantId(applicantDto.getApplicantId())
                .name(applicantDto.getName())
                .email(applicantDto.getEmail())
                .contactNumber(applicantDto.getContactNumber())
                .portfolioLink(applicantDto.getPortfolioLink())
                .resume(applicantDto.getResume()!=null?Base64.getDecoder().decode(applicantDto.getResume()):null)
                .coverLetter(applicantDto.getCoverLetter())
                .appliedTime(applicantDto.getAppliedTime())
                .interViewTime(applicantDto.getInterViewTime())
                .applicationStatus(applicantDto.getApplicationStatus())
                .build();
    }
}
