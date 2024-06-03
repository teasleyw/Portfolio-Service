package com.FrostMilano.Portfolio.mappers;

import com.FrostMilano.Portfolio.dtos.JobCandidateDto;
import com.FrostMilano.Portfolio.dtos.WorkHistoryDto;
import com.FrostMilano.Portfolio.entites.JobCandidates;
import com.FrostMilano.Portfolio.entites.WorkHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkHistoryMapper {
    WorkHistoryDto toWorkHistoryDto(WorkHistory workHistory);

    List<WorkHistoryDto> toWorkHistoryDtos(List<WorkHistory> workHistories);
    WorkHistory toWorkHistoryEntity(WorkHistoryDto workHistoryEntity);

}
