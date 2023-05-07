package com.imola.saraine.lajos.module5.repository;

import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HeadacheRepository extends JpaRepository<Headache, Long> {

    List<Headache> findHeadacheByOccurance(LocalDate occurance);

    List<Headache> findHeadacheByType(HeadacheType type);
}
