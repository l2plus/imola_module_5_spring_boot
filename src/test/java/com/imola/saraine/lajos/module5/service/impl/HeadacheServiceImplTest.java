package com.imola.saraine.lajos.module5.service.impl;

import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;
import com.imola.saraine.lajos.module5.repository.HeadacheRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeadacheServiceImplTest {

    private static final LocalDate DATE_TWO_MATCHES = LocalDate.of(2023, 2, 5);
    private static final LocalDate DATE_ONE_MATCH = LocalDate.of(2023, 3, 4);
    private static final Headache HEADACHE_1 = Headache.builder()
            .id(1L)
            .type(HeadacheType.DULL)
            .strength(5)
            .occurance(DATE_TWO_MATCHES)
            .build();
    private static final Headache HEADACHE_2 = Headache.builder()
            .id(2L)
            .type(HeadacheType.PULSING)
            .strength(5)
            .occurance(DATE_TWO_MATCHES)
            .build();
    private static final Headache HEADACHE_3 = Headache.builder()
            .id(3L)
            .type(HeadacheType.DULL)
            .strength(5)
            .occurance(DATE_ONE_MATCH)
            .build();

    @Mock
    private HeadacheRepository repositoryMock;
    @InjectMocks
    private HeadacheServiceImpl underTest;


    @Test
    void markDownHeadache_whenHappyPath_returnSavedHeadache() {
        // GIVEN
        Headache expected = HEADACHE_1;
        when(repositoryMock.save(expected)).thenReturn(expected);

        // WHEN
        Headache actual = underTest.markDownHeadache(expected);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void changeHeadacheStrength_whenHappyPath_returnSavedHeadache() {
        // GIVEN
        Headache expected = Headache.builder()
                .id(1L)
                .type(HeadacheType.DULL)
                .strength(8)
                .occurance(LocalDate.of(2023, 2, 5))
                .build();
        when(repositoryMock.getReferenceById(1L)).thenReturn(HEADACHE_1);
        when(repositoryMock.save(expected)).thenReturn(expected);

        // WHEN
        Headache actual = underTest.changeHeadacheStrength(1L, 8);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void changeHeadacheStrength_whenExceptionThrown_returnNull() {
        // GIVEN
        Headache expected = Headache.builder()
                .id(1L)
                .type(HeadacheType.DULL)
                .strength(8)
                .occurance(LocalDate.of(2023, 2, 5))
                .build();
        when(repositoryMock.getReferenceById(1L)).thenThrow(RuntimeException.class);

        // WHEN
        Headache actual = underTest.changeHeadacheStrength(1L, 8);

        // THEN
        assertNull(actual);
    }

    @Test
    void deleteHeadache_whenHappyPath_returnTrue() {
        // GIVEN
        when(repositoryMock.existsById(HEADACHE_1.getId())).thenReturn(false);

        // WHEN
        boolean actual = underTest.deleteHeadache(HEADACHE_1.getId());

        // THEN
        assertTrue(actual);
    }

    @Test
    void deleteHeadache_whenDeleteFails_returnFalse() {
        // GIVEN
        when(repositoryMock.existsById(HEADACHE_1.getId())).thenReturn(true);

        // WHEN
        boolean actual = underTest.deleteHeadache(HEADACHE_1.getId());

        // THEN
        assertFalse(actual);
    }

    @Test
    void lookUpHeadacheById_whenHappyPath_returnRequestedHeadache() {
        // GIVEN
        Headache expected = HEADACHE_1;
        when(repositoryMock.findById(HEADACHE_1.getId())).thenReturn(Optional.of(HEADACHE_1));

        // WHEN
        Headache actual = underTest.lookUpHeadache(HEADACHE_1.getId());

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void lookUpHeadacheByDate_whenHappyPath_returnRequestedHeadaches() {
        // GIVEN
        List<Headache> expected = List.of(HEADACHE_1, HEADACHE_2);
        when(repositoryMock.findHeadacheByOccurance(DATE_TWO_MATCHES)).thenReturn(expected);

        // WHEN
        List<Headache> actual = underTest.lookUpHeadache(DATE_TWO_MATCHES);

        // THEN
        assertEquals(expected, actual);
        verify(repositoryMock, times(1)).findHeadacheByOccurance(DATE_TWO_MATCHES);
    }

    @Test
    void lookUpHeadacheByType_whenHappyPath_returnRequestedHeadaches() {
        // GIVEN
        List<Headache> expected = List.of(HEADACHE_1, HEADACHE_3);
        HeadacheType dull = HeadacheType.DULL;
        when(repositoryMock.findHeadacheByType(dull)).thenReturn(expected);

        // WHEN
        List<Headache> actual = underTest.lookUpHeadache(dull);
        assertEquals(expected, actual);

        // THEN
        verify(repositoryMock, times(1)).findHeadacheByType(dull);

    }

    @Test
    void allHeadaches_whenHappyPath_returnAllHeadache() {
        // GIVEN
        List<Headache> expected = List.of(HEADACHE_1, HEADACHE_2, HEADACHE_3);
        when(repositoryMock.findAll()).thenReturn(expected);

        // WHEN
        List<Headache> actual = underTest.allHeadaches();

        // THEN
        assertEquals(expected, actual);
        verify(repositoryMock, times(1)).findAll();
    }
}