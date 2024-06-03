package com.FrostMilano.Portfolio.repositories;

import com.FrostMilano.Portfolio.entites.JobCandidates;
import com.FrostMilano.Portfolio.entites.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobCandidatesRepository  extends JpaRepository<JobCandidates, Long> {
    List<JobCandidates> findByUserId(Long userId);
    List<JobCandidates> findByJobId(Long userId);
    Optional<JobCandidates> findByJobIdAndUserId(Long jobId, Long userId);


}
