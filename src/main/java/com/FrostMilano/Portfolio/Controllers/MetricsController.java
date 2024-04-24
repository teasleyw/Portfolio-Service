package com.FrostMilano.Portfolio.controllers;
import com.FrostMilano.Portfolio.dtos.MetricsDTO;
import com.FrostMilano.Portfolio.services.MetricsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricsDTO> getMetricsById(@PathVariable("id") Long id) {
        MetricsDTO metricsDTO = metricsService.getMetricsById(id);
        return ResponseEntity.ok(metricsDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MetricsDTO>> getMetricsByUserId(@PathVariable("userId") Long userId) {
        List<MetricsDTO> metricsDTOs = metricsService.getMetricsByUserId(userId);
        return ResponseEntity.ok(metricsDTOs);
    }

    @PostMapping
    public ResponseEntity<MetricsDTO> createMetrics(@Valid @RequestBody MetricsDTO metricsDTO) {
        MetricsDTO createdMetricsDTO = metricsService.createMetrics(metricsDTO);
        return new ResponseEntity<>(createdMetricsDTO, HttpStatus.CREATED);
    }

    // Add other CRUD operations as needed
}