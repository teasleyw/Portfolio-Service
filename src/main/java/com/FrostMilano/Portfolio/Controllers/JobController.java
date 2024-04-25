package com.FrostMilano.Portfolio.controllers;

import com.FrostMilano.Portfolio.dtos.JobCandidateDto;
import com.FrostMilano.Portfolio.dtos.JobDto;
import com.FrostMilano.Portfolio.dtos.SignUpDto;
import com.FrostMilano.Portfolio.dtos.UserDto;
import com.FrostMilano.Portfolio.services.JobService;
import com.FrostMilano.Portfolio.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobController {
    private final UserService userService;
    private final JobService jobService;
    @PostMapping("/jobs")
    public ResponseEntity<JobDto> createJob(@RequestBody @Valid JobDto job) {
        JobDto createdJob = userService.createJob(job);
        return ResponseEntity.created(URI.create("/jobs/" + createdJob.getId())).body(createdJob);
    }
    @GetMapping("/jobs")
    public ResponseEntity<List<JobDto>>getAllJobs() {
        List<JobDto> jobs = jobService.getAllJobs();
        return ResponseEntity.ok().body(jobs);
    }

    @GetMapping("/jobs/candidates/{jobId}")
    public ResponseEntity<List<JobCandidateDto>>getAllJobsCandidates(@PathVariable Long jobId) {
        List<JobCandidateDto> jobCandidates = jobService.getJobCandidates(jobId);
        return ResponseEntity.ok().body(jobCandidates);
    }
    @PostMapping("/jobs/candidates")
    public ResponseEntity<JobCandidateDto>addJobCandidate(@RequestBody @Valid JobCandidateDto job) {
        JobCandidateDto addedJobCandidate = jobService.addJobCandidate(job);
        return ResponseEntity.ok().body(addedJobCandidate);
    }
}
