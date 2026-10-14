
package com.tourism.board.controller;

import com.tourism.board.entity.TourismEntry;
import com.tourism.board.repository.TourismRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tourism")
public class TourismController {

    private final TourismRepository repository;

    @Value("${app.auth-token}")
    private String validToken;

    public TourismController(TourismRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> registerTourism(
            @RequestHeader(
                value = "X-Auth-Token",
                required = false
            ) String token,
            @Valid @RequestBody TourismEntry entry) {

        if (token == null || !validToken.equals(token)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "status", 500,
                        "message", "Token validation failed"
                    ));
        }
        TourismEntry savedEntry = repository.save(entry);
        return ResponseEntity.ok(
                Map.of(
                    "status", 200,
                    "message", "Tourist created successfully",
                    "tourist", savedEntry
                )
        );
    }
}
