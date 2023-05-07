package com.imola.saraine.lajos.module5;

import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;
import com.imola.saraine.lajos.module5.repository.HeadacheRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

@DataJpaTest
class Module5ApplicationTests {

	@Test
	void contextLoads() {}

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private HeadacheRepository headacheRepository;

	@Test
	void deleteHeadacheById_shouldRemoveHeadache() {
		// Given
		Headache headache = Headache.builder()
				.strength(2)
				.occurance(LocalDate.of(2022, 1, 1))
				.type(HeadacheType.MIGRAINE)
				.build();
		Long id = entityManager.persistAndGetId(headache, Long.class);

		// When
		headacheRepository.deleteById(id);

		// Then
		Headache deletedHeadache = entityManager.find(Headache.class, id);
		assertThat(deletedHeadache).isNull();
	}

	@Test
	void saveHeadache_shouldPersistHeadache() {
		// Given
		LocalDate NOW = LocalDate.now();
		Headache expected = Headache.builder()
				.type(HeadacheType.DULL)
				.strength(1)
				.occurance(NOW)
				.build();

		// When
		Headache actual = headacheRepository.save(expected);

		// Then
		Headache persistedHeadache = entityManager.find(Headache.class, actual.getId());
		assertThat(persistedHeadache).isNotNull();
		assertEquals(expected, actual);
		assertEquals(actual, persistedHeadache);
		assertEquals(expected, persistedHeadache);
	}
}
