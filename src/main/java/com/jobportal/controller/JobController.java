package com.jobportal.controller;

import com.jobportal.dtos.ProfileDto;
import com.jobportal.entity.JobDto;
import com.jobportal.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
}
