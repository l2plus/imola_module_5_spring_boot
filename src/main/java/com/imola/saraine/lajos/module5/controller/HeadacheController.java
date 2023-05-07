package com.imola.saraine.lajos.module5.controller;


import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;
import com.imola.saraine.lajos.module5.service.HeadacheService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class HeadacheController {

    private final HeadacheService service;

    @GetMapping("/headaches")
    public ResponseEntity<List<Headache>> allHeadaches() {
        return ResponseEntity.ok(service.allHeadaches());
    }

    @DeleteMapping("/headaches/{id}")
    public ResponseEntity<String> deleteHeadache(@PathVariable Long id) {
        boolean isDeleted = service.deleteHeadache(id);
        return (isDeleted) ? ResponseEntity.ok("Headache deleted with id " + id) : ResponseEntity.ok("No headache with id " + id + " deleted.");
    }

    @PostMapping("/headaches")
    public ResponseEntity<Headache> deleteHeadache(Headache headache) {
        return ResponseEntity.ok(service.markDownHeadache(headache));
    }
}
