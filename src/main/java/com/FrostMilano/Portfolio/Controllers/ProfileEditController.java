package com.FrostMilano.Portfolio.controllers;

import com.FrostMilano.Portfolio.entites.User;
import com.FrostMilano.Portfolio.exceptions.AppException;
import com.FrostMilano.Portfolio.repositories.UserRepository;
import com.FrostMilano.Portfolio.services.UserService;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class ProfileEditController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/{userId}/profile-picture")
    @Transactional
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable Long userId) {
        // Fetch the user's profile picture from the database
        byte[] profilePicture = userService.getProfilePicture(userId);

        if (profilePicture == null) {
            // Return 404 Not Found if the profile picture is not found
            return ResponseEntity.notFound().build();
        }

        // Return the profile picture with appropriate headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(profilePicture.length);

        return new ResponseEntity<>(profilePicture, headers, HttpStatus.OK);
    }

    @PostMapping("/{userId}/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(
            @PathVariable Long userId,
            @RequestParam("profilePicture") MultipartFile file
    ) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new AppException("User not found", HttpStatus.BAD_REQUEST));


            // Convert MultipartFile to byte array
            byte[] profilePicture = file.getBytes();

            // Set the profile picture for the user
            user.setProfilePicture(profilePicture);

            // Save the user with the updated profile picture
            userRepository.save(user);

            return ResponseEntity.ok("Profile picture uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile picture");
        }
    }


    @PostMapping("/{userId}/resume")
    @Transactional
    public ResponseEntity<String> uploadResume(
            @PathVariable Long userId,
            @RequestParam("resume") MultipartFile file
    ) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new AppException("User not found", HttpStatus.BAD_REQUEST));

            // Convert MultipartFile to byte array
            byte[] resume = file.getBytes();

            // Set the resume for the user
            user.setResume(resume);

            // Save the user with the updated profile picture
            userRepository.save(user);

            return ResponseEntity.ok("Resume uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload resume");
        }
    }

    @GetMapping("/{userId}/resume")
    @Transactional
    public ResponseEntity<byte[]> getResume(
            @PathVariable Long userId
    ) {
        // Fetch the user's resume from the database
        byte[] resume = userService.getResume(userId);

        if (resume == null) {
            // Return 404 Not Found if the profile picture is not found
            return ResponseEntity.notFound().build();
        }

        // Return the profile picture with appropriate headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(resume.length);

        return new ResponseEntity<>(resume, headers, HttpStatus.OK);

    }
}
