package com.FrostMilano.Portfolio.services;

import com.FrostMilano.Portfolio.dtos.*;
import com.FrostMilano.Portfolio.entites.Job;
import com.FrostMilano.Portfolio.exceptions.AppException;
import com.FrostMilano.Portfolio.mappers.JobMapper;
import com.FrostMilano.Portfolio.mappers.UserMapper;
import com.FrostMilano.Portfolio.repositories.JobRepository;
import com.FrostMilano.Portfolio.repositories.UserRepository;
import com.FrostMilano.Portfolio.entites.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {



    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;


    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByLogin(userDto.login());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }
    public JobDto createJob (JobDto jobDto) {
        Job job = jobMapper.createJobToJob(jobDto);

        Job savedJob = jobRepository.save(job);

        return jobMapper.toJobDto(savedJob);
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
    public byte[] getProfilePicture(Long userId) {
        // Retrieve the user entity from the repository
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Return the profile picture byte array from the user entity
            return user.getProfilePicture();
        } else {
            // Return null if the user with the given ID is not found
            return null;
        }
    }
    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobMapper.toJobDtos(jobs);

    }

    public List<CandidateDto> getAllCandidates() {
        List<User> users  = userRepository.findByRole("candidate");
        return userMapper.toCandidateDtos(users);
    }
    public byte[] getResume(Long userId) {
        // Retrieve the user entity from the repository
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Return the Resume byte array from the user entity
            return user.getResume();
        } else {
            // Return null if the user with the given ID is not found
            return null;
        }
    }

}
