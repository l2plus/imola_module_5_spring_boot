package com.imola.saraine.lajos.module5.controller;

import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;
import com.imola.saraine.lajos.module5.service.HeadacheService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/headaches")
@AllArgsConstructor
public class HeadacheController {

    private final HeadacheService service;

    @GetMapping("/secured")
    public String checkIfSecurityWorks(){
        return "Works with selected security profile setting.";
    }

    @GetMapping("/")
    public ResponseEntity<List<Headache>> allHeadaches() {
        return ResponseEntity.ok(service.allHeadaches());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHeadache(@PathVariable Long id) {
        boolean isDeleted = service.deleteHeadache(id);
        return (isDeleted) ? ResponseEntity.ok("Headache deleted with id " + id) : ResponseEntity.ok("No headache with id " + id + " deleted.");
    }

    @PostMapping
    public ResponseEntity<Headache> markDownHeadache(@RequestBody Headache headache) {
        return new ResponseEntity<>(service.markDownHeadache(headache), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/strength/{strength}")
    public ResponseEntity<Headache> changeHeadacheStrength(@PathVariable Long id, @PathVariable int strength) {
        return ResponseEntity.ok(service.changeHeadacheStrength(id, strength));
    }
    @PostMapping("/headaches")
    public ResponseEntity<Headache> deleteHeadache(Headache headache) {
        return ResponseEntity.ok(service.markDownHeadache(headache));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Headache>> lookUpHeadache(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(service.lookUpHeadache(localDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Headache> lookUpHeadache(@PathVariable Long id) {
        return ResponseEntity.ok(service.lookUpHeadache(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Headache>> lookUpHeadache(@PathVariable HeadacheType type) {
        return ResponseEntity.ok(service.lookUpHeadache(type));
    }
}
