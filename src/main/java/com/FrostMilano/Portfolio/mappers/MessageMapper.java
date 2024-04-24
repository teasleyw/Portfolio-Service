package com.FrostMilano.Portfolio.mappers;

import com.FrostMilano.Portfolio.dtos.JobDto;
import com.FrostMilano.Portfolio.dtos.MessageDto;
import com.FrostMilano.Portfolio.dtos.MessageRequestDto;
import com.FrostMilano.Portfolio.entites.Job;
import com.FrostMilano.Portfolio.entites.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDto toMessageDto(Message message);

    List<MessageDto> toMessageDtoList(List<Message> messages);

    Message messageDtoToMessage(MessageRequestDto messageDto);
}
