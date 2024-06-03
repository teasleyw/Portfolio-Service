package com.FrostMilano.Portfolio.controllers;

import com.FrostMilano.Portfolio.dtos.JobCandidateDto;
import com.FrostMilano.Portfolio.dtos.JobDto;
import com.FrostMilano.Portfolio.dtos.SignUpDto;
import com.FrostMilano.Portfolio.dtos.UserDto;
import com.FrostMilano.Portfolio.entites.JobCandidates;
import com.FrostMilano.Portfolio.mappers.JobCandidateMapper;
import com.FrostMilano.Portfolio.mappers.JobMapper;
import com.FrostMilano.Portfolio.repositories.JobCandidatesRepository;
import com.FrostMilano.Portfolio.repositories.JobRepository;
import com.FrostMilano.Portfolio.repositories.UserRepository;
import com.FrostMilano.Portfolio.services.JobService;
import com.FrostMilano.Portfolio.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class JobController {
    private final UserService userService;
    private final JobService jobService;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobCandidatesRepository jobCandidatesRepository;
    private final JobCandidateMapper jobCandidateMapper;
    private final JobMapper jobMapper;
    @PostMapping("/jobs")
    public ResponseEntity<JobDto> createJob(@RequestBody @Valid JobDto job) {
        if (!userRepository.existsById(job.getCompanyId())) {
            return ResponseEntity.notFound().build();
        }
        JobDto createdJob = userService.createJob(job);
        return ResponseEntity.created(URI.create("/jobs/" + createdJob.getId())).body(createdJob);
    }
    @GetMapping("/jobs")
    public ResponseEntity<List<JobDto>>getAllJobs() {
        List<JobDto> jobs = jobService.getAllJobs();
        return ResponseEntity.ok().body(jobs);
    }

    @GetMapping("/jobs/company/{companyId}")
    public ResponseEntity<List<JobDto>> getJobsByCompanyId(@PathVariable Long companyId) {
        List<JobDto> jobs = jobService.getJobsByCompanyId(companyId);
        return ResponseEntity.ok().body(jobs);
    }

    @GetMapping("/jobs/candidates/{jobId}")
    public ResponseEntity<List<JobCandidateDto>>getAllJobsCandidates(@PathVariable Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            return ResponseEntity.notFound().build();
        }
        List<JobCandidateDto> jobCandidates = jobService.getJobCandidates(jobId);
        return ResponseEntity.ok().body(jobCandidates);
    }
    @PostMapping("/jobs/candidates")
    public ResponseEntity<JobCandidateDto> addOrUpdateJobCandidate(@RequestBody @Valid JobCandidateDto jobCandidate) {
        // Convert DTO to entity
        JobCandidates job = jobCandidateMapper.addCandidateDtoToEntity(jobCandidate);

        // Check if a job with the given ID exists
        Optional<JobCandidates> existingJobCandidate = jobCandidatesRepository.findByJobIdAndUserId(job.getJobId(), job.getUserId());

        if (existingJobCandidate.isPresent()) {
            // If a candidate with the given job ID and user ID exists, update it
            JobCandidates existingCandidate = existingJobCandidate.get();
            // Update the existing entry with new data
            jobCandidate.setId(existingCandidate.getId());
            // (You might need to implement an update method in your mapper or repository)
            jobCandidateMapper.updateCandidateFromDto(jobCandidate,existingCandidate);
            // Save the updated entry
            JobCandidates updatedJob = jobCandidatesRepository.save(existingCandidate);

            // Convert the updated entity back to DTO
            JobCandidateDto updatedJobCandidate = jobCandidateMapper.toJobCandidateDto(updatedJob);

            // Return response with updated candidate
            return ResponseEntity.ok().body(updatedJobCandidate);
        } else {
            // If no candidate with the given job ID and user ID exists, create a new one
            if (!jobRepository.existsById(job.getJobId())) {
                return ResponseEntity.notFound().build();
            }
            if (!userRepository.existsById(job.getUserId())) {
                return ResponseEntity.notFound().build();
            }
            // Save the new candidate
            JobCandidates savedJob = jobCandidatesRepository.save(job);

            // Convert the saved entity back to DTO
            JobCandidateDto addedJobCandidate = jobCandidateMapper.toJobCandidateDto(savedJob);

            // Return response with newly added candidate
            return ResponseEntity.ok().body(addedJobCandidate);
        }
    }
}
