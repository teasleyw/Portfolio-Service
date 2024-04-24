package com.FrostMilano.Portfolio.repositories;

import com.FrostMilano.Portfolio.entites.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetricsRepository extends JpaRepository<Metrics, Long> {
    List<Metrics> findByUserId(Long userId);

}
