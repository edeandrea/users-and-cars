package org.acme.common.persistence;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestTransaction
class UserAndCarEntityRepositoryTests {
	@Inject
	UserAndCarEntityRepository userAndCarEntityRepository;

	@Test
	public void listAllForUserIdNothingFound() {
		assertThat(this.userAndCarEntityRepository.listAllForUserId(1L))
			.isNotNull()
			.isEmpty();
	}

	@Test
	public void listAllForUserIdFound() {
		this.userAndCarEntityRepository.persist(new UserAndCarEntity(1L, 1L), new UserAndCarEntity(2L, 1L));
		List<UserAndCarEntity> entities = this.userAndCarEntityRepository.listAllForUserId(1L);

		assertThat(entities)
			.isNotNull()
			.isNotEmpty()
			.hasSize(1);

		assertThat(entities.get(0))
			.isNotNull();

		assertThat(entities.get(0).getId())
			.isNotNull()
			.extracting(UserCarKey::getUserId, UserCarKey::getCarId)
			.containsExactly(1L, 1L);
	}

	@Test
	public void listAllForCarIdNothingFound() {
		assertThat(this.userAndCarEntityRepository.listAllForCarId(1L))
			.isNotNull()
			.isEmpty();
	}

	@Test
	public void listAllForCarIdFound() {
		this.userAndCarEntityRepository.persist(new UserAndCarEntity(1L, 1L), new UserAndCarEntity(2L, 1L));
		List<UserAndCarEntity> entities = this.userAndCarEntityRepository.listAllForCarId(1L);

		assertThat(entities)
			.isNotNull()
			.isNotEmpty()
			.hasSize(2);

		assertThat(entities.get(0))
			.isNotNull();

		assertThat(entities.get(1))
			.isNotNull();

		var entityIds = entities.stream()
			.map(UserAndCarEntity::getId)
			.collect(Collectors.toList());

		assertThat(entityIds)
			.isNotNull()
			.isNotEmpty()
			.extracting(UserCarKey::getUserId, UserCarKey::getCarId)
			.containsExactly(
				tuple(1L, 1L),
				tuple(2L, 1L)
			);
	}
}
