package com.imola.saraine.lajos.module5.controller;


import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.service.HeadacheService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class HeadacheController {

    private final HeadacheService service;

    @GetMapping("/headaches")
    public ResponseEntity<List<Headache>> allHeadaches() {
        return ResponseEntity.ok(service.allHeadaches());
    }
}
