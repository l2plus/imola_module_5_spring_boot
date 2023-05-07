package com.imola.saraine.lajos.module5.service;

import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;

import java.time.LocalDate;
import java.util.List;

public interface HeadacheService {

    public Headache markDownHeadache (Headache headache);

    public Headache changeHeadacheStrength(Long id, int strength);

    public boolean deleteHeadache(Long id);

    public List<Headache> lookUpHeadache(LocalDate date);

    public Headache lookUpHeadache(Long id);

    public List<Headache> lookUpHeadache(HeadacheType type);

    public List<Headache> allHeadaches();
}
