package com.FrostMilano.Portfolio.controllers;
import com.FrostMilano.Portfolio.dtos.MetricsDTO;
import com.FrostMilano.Portfolio.entites.Metrics;
import com.FrostMilano.Portfolio.entites.QandA;
import com.FrostMilano.Portfolio.mappers.MetricsMapper;
import com.FrostMilano.Portfolio.repositories.MetricsRepository;
import com.FrostMilano.Portfolio.repositories.UserRepository;
import com.FrostMilano.Portfolio.services.MetricsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MetricsController {

    private final MetricsService metricsService;
    private final MetricsMapper metricsMapper;
    private final UserRepository userRepository;
    private final MetricsRepository metricsRepository;


    @GetMapping("/{id}")
    public ResponseEntity<MetricsDTO> getMetricsById(@PathVariable("id") Long id) {
        MetricsDTO metricsDTO = metricsService.getMetricsById(id);
        return ResponseEntity.ok(metricsDTO);
    }

    @GetMapping("/metrics/{userId}")
    public ResponseEntity<List<MetricsDTO>> getMetricsByUserId(@PathVariable("userId") Long userId) {
        // Check if the user exists
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        // Fetch metrics by user ID
        List<Metrics> metrics = metricsRepository.findByUserId(userId);
        // Convert to DTOs
        List<MetricsDTO> metricsDTOs = metricsMapper.toMetricsDTO(metrics);
        // Return response
        return ResponseEntity.ok().body(metricsDTOs);
    }
    @PostMapping("/metrics")
    public ResponseEntity<MetricsDTO> createOrUpdateMetrics(@RequestBody @Valid MetricsDTO metricsDTO) {
        // Check if the user exists
        if (!userRepository.existsById(metricsDTO.getUserId())) {
            return ResponseEntity.notFound().build();
        }

        // Convert DTO to entity
        Metrics metrics = metricsMapper.toMetricsEntity(metricsDTO);

        // Check if a Metrics with the provided ID already exists
        if (metrics.getId() != null) {
            Optional<Metrics> existingMetricsOptional = metricsRepository.findById(metrics.getId());

            if (existingMetricsOptional.isPresent()) {
                Metrics existingMetrics = existingMetricsOptional.get();
                existingMetrics.setYear(metrics.getYear());
                existingMetrics.setSalesCycle(metrics.getSalesCycle());
                existingMetrics.setTargetVerticals(metrics.getTargetVerticals());
                existingMetrics.setBuyerPersona(metrics.getBuyerPersona());
                existingMetrics.setAvgDeal(metrics.getAvgDeal());
                existingMetrics.setAttainment(metrics.getAttainment());
                existingMetrics.setQuota(metrics.getQuota());
                // Update any other fields as needed
                existingMetrics = metricsRepository.save(existingMetrics);
                // Convert the updated entity back to DTO
                MetricsDTO updatedMetricsDTO = metricsMapper.toMetricDto(existingMetrics);
                // Return response with the updated Metrics
                return ResponseEntity.ok().body(updatedMetricsDTO);
            }
        }

        // If it doesn't exist or the ID is null, save the new Metrics
        Metrics savedMetrics = metricsRepository.save(metrics);
        // Convert the saved entity back to DTO
        MetricsDTO savedMetricsDTO = metricsMapper.toMetricDto(savedMetrics);
        // Return response with the newly created Metrics
        return ResponseEntity.ok().body(savedMetricsDTO);
    }
    @DeleteMapping("/metrics/{id}")
    public ResponseEntity<?> deleteMetrics(@PathVariable Long id) {
        // Check if the work history exists
        Optional<Metrics> metricsOptional = metricsRepository.findById(id);
        if (metricsOptional.isEmpty()) {
            // If not found, return 404 Not Found status
            return ResponseEntity.notFound().build();
        }

        // If found, delete the work history
        metricsRepository.deleteById(id);
        // Return 204 No Content status to indicate successful deletion
        return ResponseEntity.noContent().build();
    }

}