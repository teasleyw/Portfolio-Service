package com.FrostMilano.Portfolio.mappers;

import com.FrostMilano.Portfolio.dtos.MetricsDTO;
import com.FrostMilano.Portfolio.entites.Metrics;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetricsMapper {
    List<MetricsDTO> toMetricsDTO(List<Metrics> metrics);
    Metrics createMetric(MetricsDTO metrics);

    MetricsDTO toMetricDto(Metrics metrics);


}
