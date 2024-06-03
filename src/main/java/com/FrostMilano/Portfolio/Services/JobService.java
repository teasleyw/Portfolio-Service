package com.FrostMilano.Portfolio.services;

import com.FrostMilano.Portfolio.dtos.JobCandidateDto;
import com.FrostMilano.Portfolio.dtos.JobDto;
import com.FrostMilano.Portfolio.entites.Job;
import com.FrostMilano.Portfolio.entites.JobCandidates;
import com.FrostMilano.Portfolio.mappers.JobCandidateMapper;
import com.FrostMilano.Portfolio.mappers.JobMapper;
import com.FrostMilano.Portfolio.repositories.JobCandidatesRepository;
import com.FrostMilano.Portfolio.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobCandidatesRepository jobCandidatesRepository;
    private final JobMapper jobMapper;
    private final JobCandidateMapper jobCandidateMapper;
    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobMapper.toJobDtos(jobs);
    }
    public List<JobCandidateDto> getJobCandidates(Long jobId) {
        List<JobCandidates> jobCandidates = jobCandidatesRepository.findByJobId(jobId);
        return jobCandidateMapper.toJobCandidateDtos(jobCandidates);

    }
    public JobDto createJob (JobDto jobDto) {
        Job job = jobMapper.createJobToJob(jobDto);

        Job savedJob = jobRepository.save(job);

        return jobMapper.toJobDto(savedJob);
    }
    public List<JobDto> getJobsByCompanyId(Long companyId) {
        // Implement logic to fetch jobs by companyId from your repository
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        // Convert the list of Job entities to a list of JobDto objects
        return jobs.stream()
                .map(jobMapper::toJobDto)
                .collect(Collectors.toList());
    }

}
