package com.FrostMilano.Portfolio.mappers;

import com.FrostMilano.Portfolio.dtos.QandADto;
import com.FrostMilano.Portfolio.dtos.WorkHistoryDto;
import com.FrostMilano.Portfolio.entites.QandA;
import com.FrostMilano.Portfolio.entites.WorkHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QandAMapper {

    QandADto toQandADto(QandA qandA);
    List<QandADto> toQandADtos(List<QandA> QandAs);
    QandA toQandAEntity(QandADto QandAEntity);

}
