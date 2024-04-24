package com.FrostMilano.Portfolio.controllers;

import com.FrostMilano.Portfolio.dtos.JobDto;
import com.FrostMilano.Portfolio.dtos.SignUpDto;
import com.FrostMilano.Portfolio.dtos.UserDto;
import com.FrostMilano.Portfolio.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobController {
    private final UserService userService;
    @PostMapping("/jobs")
    public ResponseEntity<JobDto> createJob(@RequestBody @Valid JobDto job) {
        JobDto createdJob = userService.createJob(job);
        return ResponseEntity.created(URI.create("/jobs/" + createdJob.getId())).body(createdJob);
    }
    @GetMapping("/jobs")
    public ResponseEntity<List<JobDto>>getAllJobs() {
        List<JobDto> jobs = userService.getAllJobs();
        return ResponseEntity.ok().body(jobs);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDto>>getAllJobsCandidaates() {
        List<JobDto> jobs = userService.getAllJobs();
        return ResponseEntity.ok().body(jobs);
    }
}
