package com.FrostMilano.Portfolio.mappers;

import com.FrostMilano.Portfolio.dtos.JobCandidateDto;
import com.FrostMilano.Portfolio.dtos.PoemDto;
import com.FrostMilano.Portfolio.entites.JobCandidates;
import com.FrostMilano.Portfolio.entites.Poem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PoemMapper {
    PoemDto toPoemDto(Poem poem);

    List<PoemDto> toPoemDtoList(List<Poem> poems);

    Poem toPoemEntity(PoemDto poemDto);
    // Method to update an existing JobCandidates entity from a JobCandidateDto
    void updatePoemFromDto(PoemDto dto, @MappingTarget Poem entity);
}
