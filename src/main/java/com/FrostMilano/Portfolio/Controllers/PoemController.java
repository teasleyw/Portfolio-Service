package com.FrostMilano.Portfolio.Controllers;

import com.FrostMilano.Portfolio.Services.EmailService;
import com.FrostMilano.Portfolio.dtos.MetricsDTO;
import com.FrostMilano.Portfolio.dtos.PoemDto;
import com.FrostMilano.Portfolio.entites.Metrics;
import com.FrostMilano.Portfolio.entites.Poem;
import com.FrostMilano.Portfolio.mappers.MetricsMapper;
import com.FrostMilano.Portfolio.mappers.PoemMapper;
import com.FrostMilano.Portfolio.repositories.MetricsRepository;
import com.FrostMilano.Portfolio.repositories.PoemRepository;
import com.FrostMilano.Portfolio.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/poems")
public class PoemController {

    private final PoemMapper poemMapper;
    private final PoemRepository poemRepository;
    private final EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendPoemEmail(@RequestBody @Valid PoemDto poemDto) {
        try {
            // Send an email with the poemDto
            emailService.sendPoemEmail(poemDto);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }
    @PostMapping("/createorupdate")
    public ResponseEntity<PoemDto> createOrUpdatePoem(@RequestBody @Valid PoemDto poemDto) {
        // Convert DTO to entity
        Poem poem = poemMapper.toPoemEntity(poemDto);

        // Check if a Poem with the provided ID already exists
        if (poem.getId() != null) {
            Optional<Poem> existingPoemOptional = poemRepository.findById(poem.getId());

            if (existingPoemOptional.isPresent()) {
                Poem existingPoem = existingPoemOptional.get();
                existingPoem.setDate(poem.getDate());
                existingPoem.setPoem(poem.getPoem());
                existingPoem.setTitle(poem.getTitle());
                existingPoem.setAuthor(poem.getAuthor());
                // Update any other fields as needed
                existingPoem = poemRepository.save(existingPoem);
                // Convert the updated entity back to DTO
                PoemDto updatedPoemDto = poemMapper.toPoemDto(existingPoem);
                // Return response with the updated Poem
                return ResponseEntity.ok().body(updatedPoemDto);
            }
        }

        // If it doesn't exist or the ID is null, save the new Poem
        Poem savedPoem = poemRepository.save(poem);
        // Convert the saved entity back to DTO
        PoemDto savedPoemDto = poemMapper.toPoemDto(savedPoem);
        // Return response with the newly created Poem
        return ResponseEntity.ok().body(savedPoemDto);
    }

    @DeleteMapping("/poem/delete/{id}")
    public ResponseEntity<?> deletePoem(@PathVariable Long id) {
        // Check if the poem exists
        Optional<Poem> poemOptional = poemRepository.findById(id);
        if (poemOptional.isEmpty()) {
            // If not found, return 404 Not Found status
            return ResponseEntity.notFound().build();
        }

        // If found, delete the poem
        poemRepository.deleteById(id);
        // Return 204 No Content status to indicate successful deletion
        return ResponseEntity.noContent().build();
    }

    // Additional methods to get poems can be added here
    @GetMapping("/poem/{id}")
    public ResponseEntity<PoemDto> getPoemById(@PathVariable Long id) {
        Optional<Poem> poemOptional = poemRepository.findById(id);
        if (poemOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PoemDto poemDto = poemMapper.toPoemDto(poemOptional.get());
        return ResponseEntity.ok().body(poemDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PoemDto>> getAllPoems() {
        List<Poem> poems = poemRepository.findAll();
        List<PoemDto> poemDtos = poems.stream().map(poemMapper::toPoemDto).toList();
        return ResponseEntity.ok().body(poemDtos);
    }
}