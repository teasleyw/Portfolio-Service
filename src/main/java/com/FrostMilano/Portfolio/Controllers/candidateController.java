package com.FrostMilano.Portfolio.controllers;

import com.FrostMilano.Portfolio.dtos.CandidateDto;
import com.FrostMilano.Portfolio.dtos.JobDto;
import com.FrostMilano.Portfolio.dtos.SetStatusDto;
import com.FrostMilano.Portfolio.dtos.UserDto;
import com.FrostMilano.Portfolio.entites.User;
import com.FrostMilano.Portfolio.exceptions.AppException;
import com.FrostMilano.Portfolio.mappers.UserMapper;
import com.FrostMilano.Portfolio.repositories.UserRepository;
import com.FrostMilano.Portfolio.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RequiredArgsConstructor
@RestController
public class CandidateController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/candidates")
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        List<CandidateDto> users = userService.getAllCandidates();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/{userId}/candidates/info")
    public ResponseEntity<CandidateDto> getCandidateInfo( @PathVariable Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new AppException("User not found", HttpStatus.BAD_REQUEST));


            CandidateDto candidate = userMapper.toCandidateDto(user);

            return ResponseEntity.ok(candidate);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Failed to get User", HttpStatus.BAD_REQUEST);
        }    }

    @PostMapping("/{userId}/candidates/status")
    public ResponseEntity<String> setCandidateStatus(
            @PathVariable Long userId,
            @RequestBody @Valid SetStatusDto statusDto) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new AppException("User not found", HttpStatus.BAD_REQUEST));

            // Set the resume for the user
            user.setTopStatus(statusDto.getTopStatus());

            // Save the user with the updated profile picture
            userRepository.save(user);

            return ResponseEntity.ok("Status Changed");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to set candidate Status");
        }
    }
}
 