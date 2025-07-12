package com.jobportal.controller;

import com.jobportal.dtos.ApplicantDto;
import com.jobportal.dtos.Application;
import com.jobportal.dtos.JobDto;
import com.jobportal.dtos.Response;
import com.jobportal.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/job")
@RequiredArgsConstructor
@Validated
public class JobController {
    private final JobService jobService;

    @PostMapping("/post")
    public  ResponseEntity<JobDto> postJob(@RequestBody @Valid JobDto jobDto){
        return ResponseEntity.ok(jobService.postJob(jobDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobDto>> getAllJob(){
        return ResponseEntity.ok(jobService.getAll());
    }
    @GetMapping("/get/{jobId}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long jobId){
        return ResponseEntity.ok(jobService.getJob(jobId));
    }
    @PostMapping("/apply/{jobId}")
    public ResponseEntity<Response> applyJob(@RequestBody @Valid ApplicantDto applicantDto, @PathVariable Long jobId){
        jobService.applyJob(applicantDto,jobId);
        return ResponseEntity.ok(Response.builder().message("applied Successfully").build());
    }
    @GetMapping("/postedBy/{id}")
    public ResponseEntity<List<JobDto>> getAllJobPostedBy(@PathVariable Long id){
        return ResponseEntity.ok(jobService.getAllJobPostedBy(id));
    }
    @PostMapping("/changeAppStatus")
    public ResponseEntity<Response> changeApplicantStatus(@RequestBody Application application){
        jobService.changeApplicantStatus(application);
        return ResponseEntity.ok(Response.builder().message("application status changed Successfully").build());
    }
}
