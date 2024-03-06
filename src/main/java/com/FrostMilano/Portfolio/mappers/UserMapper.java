package com.FrostMilano.Portfolio.mappers;

import com.FrostMilano.Portfolio.dtos.SignUpDto;
import com.FrostMilano.Portfolio.dtos.UserDto;
import com.FrostMilano.Portfolio.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
