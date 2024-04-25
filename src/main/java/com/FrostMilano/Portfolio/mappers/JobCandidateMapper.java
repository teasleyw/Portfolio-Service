package com.FrostMilano.Portfolio.mappers;

import com.FrostMilano.Portfolio.dtos.JobCandidateDto;
import com.FrostMilano.Portfolio.dtos.JobDto;
import com.FrostMilano.Portfolio.entites.Job;
import com.FrostMilano.Portfolio.entites.JobCandidates;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobCandidateMapper {
    JobCandidateDto toJobCandidateDto(JobCandidates job);

    List<JobCandidateDto> toJobCandidateDtos(List<JobCandidates> jobs);

    JobCandidates addCandidateDtoToEntity(JobCandidateDto jobCandidateDto);
}
