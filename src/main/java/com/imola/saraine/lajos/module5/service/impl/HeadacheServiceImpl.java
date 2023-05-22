package com.imola.saraine.lajos.module5.service.impl;

import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;
import com.imola.saraine.lajos.module5.repository.HeadacheRepository;
import com.imola.saraine.lajos.module5.service.HeadacheService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@AllArgsConstructor
public class HeadacheServiceImpl implements HeadacheService {

    private final HeadacheRepository repository;
    @Autowired
    MeterRegistry meterRegistry;

    @Override
    public Headache markDownHeadache(Headache headache) {
        meterRegistry.counter("headache.addnew.counter").increment();
        return repository.save(headache);
    }

    @Override
    public Headache changeHeadacheStrength(Long id, int strength) {
        try {
        Headache original = repository.getReferenceById(id);
        original.setStrength(strength);
        meterRegistry.counter("headache.change.counter").increment();
            return repository.save(original);
        } catch (Exception e) {
            log.info("Strength of Headache could not be changed for headache with id: " + id + "Reason: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteHeadache(Long id) {
        repository.deleteById(id);
        meterRegistry.counter("headache.delete.counter").increment();
        return !repository.existsById(id);
    }

    @Override
    public List<Headache> lookUpHeadache(LocalDate date) {
        meterRegistry.counter("headache.lookup.counter").increment();
        return repository.findHeadacheByOccurance(date);
    }

    @Override
    public Headache lookUpHeadache(Long id) {
        meterRegistry.counter("headache.lookup.counter").increment();
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No headache found with id " + id));
    }

    @Override
    public List<Headache> lookUpHeadache(HeadacheType type) {
        meterRegistry.counter("headache.lookup.counter").increment();
        return repository.findHeadacheByType(type);
    }

    @Override
    public List<Headache> allHeadaches() {
        Instant start = Instant.now();
        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        List<Headache> allHeadaches = repository.findAll();
        Metrics.timer("headache.findall.timer").record(duration);
        meterRegistry.counter("headache.lookup.counter").increment();
        return allHeadaches;
    }
}