package com.imola.saraine.lajos.module5.service.impl;

import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;
import com.imola.saraine.lajos.module5.repository.HeadacheRepository;
import com.imola.saraine.lajos.module5.service.HeadacheService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class HeadacheServiceImpl implements HeadacheService {

    private final HeadacheRepository repository;

    @Override
    public Headache markDownHeadache(Headache headache) {
        return repository.save(headache);
    }

    @Override
    public Headache changeHeadacheStrength(Long id, int strength) {
        try {
        Headache original = repository.getReferenceById(id);
        original.setStrength(strength);
            return repository.save(original);
        } catch (Exception e) {
            log.info("Strength of Headache could not be changed for headache with id: " + id + "Reason: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteHeadache(Long id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public List<Headache> lookUpHeadache(LocalDate date) {
        return repository.findHeadacheByOccurance(date);
    }

    @Override
    public Headache lookUpHeadache(Long id) {
        Optional<Headache> result = repository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new NoSuchElementException("No headache found with id " + id);
    }

    @Override
    public List<Headache> lookUpHeadache(HeadacheType type) {
        return repository.findHeadacheByType(type);
    }

    @Override
    public List<Headache> allHeadaches() {
        return repository.findAll();
    }
}