package com.FrostMilano.Portfolio.controllers;

import com.FrostMilano.Portfolio.dtos.JobCandidateDto;
import com.FrostMilano.Portfolio.dtos.WorkHistoryDto;
import com.FrostMilano.Portfolio.entites.JobCandidates;
import com.FrostMilano.Portfolio.entites.WorkHistory;
import com.FrostMilano.Portfolio.mappers.WorkHistoryMapper;
import com.FrostMilano.Portfolio.repositories.UserRepository;
import com.FrostMilano.Portfolio.repositories.WorkHistoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class WorkHistoryController {
    private final UserRepository userRepository;
    private final WorkHistoryRepository workHistoryRepository;
    private final WorkHistoryMapper workHistoryMapper;
    @GetMapping("/workhistory/{userId}")
    public ResponseEntity<List<WorkHistoryDto>> getAllJobsCandidates(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        List<WorkHistory>  workHistories = workHistoryRepository.findByUserId(userId);
        List<WorkHistoryDto> workHistoryDtos = workHistoryMapper.toWorkHistoryDtos(workHistories);
        return ResponseEntity.ok().body(workHistoryDtos);
    }
    @PostMapping("/workhistory")
    public ResponseEntity<WorkHistoryDto> createOrUpdateWorkHistory(@RequestBody @Valid WorkHistoryDto workHistoryDto) {
        // Check if the user exists
        if (!userRepository.existsById(workHistoryDto.getUserId())) {
            return ResponseEntity.notFound().build();
        }

        // Convert DTO to entity
        WorkHistory workHistory = workHistoryMapper.toWorkHistoryEntity(workHistoryDto);

        // Check if a work history with the provided ID already exists
        if (workHistory.getId() != null) {
            Optional<WorkHistory> existingWorkHistoryOptional = workHistoryRepository.findById(workHistory.getId());

            if (existingWorkHistoryOptional.isPresent()) {
                // If it exists, update the existing item
                WorkHistory existingWorkHistory = existingWorkHistoryOptional.get();
                existingWorkHistory.setCompany(workHistory.getCompany());
                existingWorkHistory.setJobTitle(workHistory.getJobTitle());
                existingWorkHistory.setStartDate(workHistory.getStartDate());
                existingWorkHistory.setEndDate(workHistory.getEndDate());
                // Update any other fields as needed
                existingWorkHistory = workHistoryRepository.save(existingWorkHistory);
                // Convert the updated entity back to DTO
                WorkHistoryDto updatedWorkHistoryDto = workHistoryMapper.toWorkHistoryDto(existingWorkHistory);
                // Return response with the updated work history
                return ResponseEntity.ok().body(updatedWorkHistoryDto);
            }

        }

        // If it doesn't exist or the ID is null, save the new work history
        WorkHistory savedWorkHistory = workHistoryRepository.save(workHistory);
        // Convert the saved entity back to DTO
        WorkHistoryDto savedWorkHistoryDto = workHistoryMapper.toWorkHistoryDto(savedWorkHistory);
        // Return response with the newly created work history
        return ResponseEntity.ok().body(savedWorkHistoryDto);
    }

    @DeleteMapping("/workhistory/{id}")
    public ResponseEntity<?> deleteWorkHistory(@PathVariable Long id) {
        // Check if the work history exists
        Optional<WorkHistory> workHistoryOptional = workHistoryRepository.findById(id);
        if (workHistoryOptional.isEmpty()) {
            // If not found, return 404 Not Found status
            return ResponseEntity.notFound().build();
        }

        // If found, delete the work history
        workHistoryRepository.deleteById(id);
        // Return 204 No Content status to indicate successful deletion
        return ResponseEntity.noContent().build();
    }

}
