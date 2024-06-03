package com.FrostMilano.Portfolio.controllers;

import com.FrostMilano.Portfolio.dtos.QandADto;
import com.FrostMilano.Portfolio.dtos.WorkHistoryDto;
import com.FrostMilano.Portfolio.entites.QandA;
import com.FrostMilano.Portfolio.entites.WorkHistory;
import com.FrostMilano.Portfolio.mappers.QandAMapper;
import com.FrostMilano.Portfolio.repositories.QandARepository;
import com.FrostMilano.Portfolio.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class QandAController {
    private final UserRepository userRepository;
    private final QandAMapper qandAMapper;
    private final QandARepository qandARepository;
    @GetMapping("/QandA/{userId}")
    public ResponseEntity<List<QandADto>> getAllJobsCandidates(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        List<QandA>  qandAs = qandARepository.findByUserId(userId);
        List<QandADto> qandADtos = qandAMapper.toQandADtos(qandAs);
        return ResponseEntity.ok().body(qandADtos);
    }
    @PostMapping("/QandA")
    public ResponseEntity<QandADto> createOrUpdateQandA(@RequestBody @Valid QandADto qandADto) {
        // Check if the user exists
        if (!userRepository.existsById(qandADto.getUserId())) {
            return ResponseEntity.notFound().build();
        }

        // Convert DTO to entity
        QandA qandA = qandAMapper.toQandAEntity(qandADto);

        // Check if a QandA with the provided ID already exists
        if (qandA.getId() != null) {
            Optional<QandA> existingQandAOptional = qandARepository.findById(qandA.getId());

            if (existingQandAOptional.isPresent()) {
                // If it exists, update the existing item
                QandA existingQandA = existingQandAOptional.get();
                existingQandA.setQuestion(qandA.getQuestion());
                existingQandA.setAnswer(qandA.getAnswer());
                // Update any other fields as needed
                existingQandA = qandARepository.save(existingQandA);
                // Convert the updated entity back to DTO
                QandADto updatedQandADto = qandAMapper.toQandADto(existingQandA);
                // Return response with the updated QandA
                return ResponseEntity.ok().body(updatedQandADto);
            }
        }

        // If it doesn't exist or the ID is null, save the new QandA
        QandA savedQandA = qandARepository.save(qandA);
        // Convert the saved entity back to DTO
        QandADto savedQandADto = qandAMapper.toQandADto(savedQandA);
        // Return response with the newly created QandA
        return ResponseEntity.ok().body(savedQandADto);
    }
    @DeleteMapping("/QandA/{id}")
    public ResponseEntity<?> deleteQandA(@PathVariable Long id) {
        // Check if the work history exists
        Optional<QandA> qandAOptional = qandARepository.findById(id);
        if (qandAOptional.isEmpty()) {
            // If not found, return 404 Not Found status
            return ResponseEntity.notFound().build();
        }

        // If found, delete the work history
        qandARepository.deleteById(id);
        // Return 204 No Content status to indicate successful deletion
        return ResponseEntity.noContent().build();
    }

}
