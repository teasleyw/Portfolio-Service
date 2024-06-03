package com.FrostMilano.Portfolio.services;

import com.FrostMilano.Portfolio.dtos.MetricsDTO;
import com.FrostMilano.Portfolio.entites.Metrics;
import com.FrostMilano.Portfolio.mappers.MetricsMapper;
import com.FrostMilano.Portfolio.repositories.MetricsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MetricsService {


    private final MetricsRepository metricsRepository;
    private final MetricsMapper metricsMapper;



    public MetricsDTO getMetricsById(Long id) {
        Metrics metrics = metricsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Metrics not found with id: " + id));
        return metricsMapper.toMetricDto(metrics);
    }


}
