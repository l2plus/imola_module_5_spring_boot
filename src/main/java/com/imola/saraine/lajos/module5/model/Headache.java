package com.imola.saraine.lajos.module5.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Builder
@Data
public class Headache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    HeadacheType type;
    int strength;
    LocalDate occurance;
}