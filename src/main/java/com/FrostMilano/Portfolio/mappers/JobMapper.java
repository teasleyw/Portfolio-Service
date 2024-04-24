package com.FrostMilano.Portfolio.mappers;

import com.FrostMilano.Portfolio.dtos.JobDto;
import com.FrostMilano.Portfolio.dtos.SignUpDto;
import com.FrostMilano.Portfolio.entites.Job;
import com.FrostMilano.Portfolio.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobDto toJobDto(Job job);

    List<JobDto> toJobDtos(List<Job> jobs);

    Job createJobToJob(JobDto jobDto);
}
