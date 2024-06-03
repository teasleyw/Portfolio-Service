package com.FrostMilano.Portfolio.repositories;

import com.FrostMilano.Portfolio.entites.JobCandidates;
import com.FrostMilano.Portfolio.entites.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {

    List<WorkHistory> findByUserId(Long userId);

}
