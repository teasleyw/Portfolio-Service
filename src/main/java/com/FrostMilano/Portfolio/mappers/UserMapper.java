package com.FrostMilano.Portfolio.mappers;

import com.FrostMilano.Portfolio.dtos.CandidateDto;
import com.FrostMilano.Portfolio.dtos.JobDto;
import com.FrostMilano.Portfolio.dtos.SignUpDto;
import com.FrostMilano.Portfolio.dtos.UserDto;
import com.FrostMilano.Portfolio.entites.Job;
import com.FrostMilano.Portfolio.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    CandidateDto toCandidateDto(User user);

    List<CandidateDto> toCandidateDtos(List<User> candidates);


    List<UserDto> toUserDtos(List<User> users);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
