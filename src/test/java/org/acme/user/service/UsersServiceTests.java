package org.acme.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.acme.car.persistence.CarEntity;
import org.acme.car.persistence.CarEntityRepository;
import org.acme.car.rest.CarDto;
import org.acme.common.domain.Make;
import org.acme.common.persistence.UserAndCarEntity;
import org.acme.common.persistence.UserAndCarEntityRepository;
import org.acme.common.persistence.UserCarKey;
import org.acme.user.persistence.UserEntity;
import org.acme.user.persistence.UserEntityRepository;
import org.acme.user.rest.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestTransaction
class UsersServiceTests {
	@Inject
	CarEntityRepository carEntityRepository;

	@Inject
	UserEntityRepository userEntityRepository;

	@Inject
	UserAndCarEntityRepository userAndCarEntityRepository;

	@Inject
	UsersService usersService;

	List<UserDto> expectedUsers;
	Map<Long, UserDto> expectedUsersMap;

	@BeforeEach
	public void beforeEach() {
		this.expectedUsers = null;
		this.expectedUsersMap = null;
	}

	@Test
	public void findUserNotFound() {
		setupTestData();
		assertThat(this.usersService.findUser(-1L))
			.isNotNull()
			.isNotPresent();
	}

	@Test
	public void findUserFound() {
		setupTestData();
		var user = this.usersService.findUser(this.expectedUsers.get(2).getId());

		assertThat(user)
			.isNotNull()
			.isPresent();

		verify(this.expectedUsers.get(2), user.get());
	}

	@Test
	public void listAllUsers() {
		setupTestData();
		var allUsers = this.usersService.getAllUsers();
		assertThat(allUsers)
			.hasSameSizeAs(this.expectedUsers);

		allUsers.forEach(user -> verify(this.expectedUsersMap.get(user.getId()), user));
	}

	private static void verify(UserDto expected, UserDto actual) {
		assertThat(actual)
			.isNotNull()
			.extracting(UserDto::getId, UserDto::getName)
			.containsExactly(expected.getId(), expected.getName());

		var expectedCars = expected.getCars();

		if (expectedCars != null) {
			var actualCars = actual.getCars();
			var expectedCarsMap = expectedCars.stream()
				.collect(Collectors.toMap(CarDto::getId, Function.identity()));

			assertThat(actualCars)
				.isNotNull()
				.hasSameSizeAs(expectedCars);

			actualCars.forEach(expectedCar -> verify(expectedCarsMap.get(expectedCar.getId()), expectedCar));
		}
	}

	private static void verify(CarDto expected, CarDto actual) {
		assertThat(actual)
			.isNotNull()
			.extracting(CarDto::getId, CarDto::getMake, CarDto::getModel)
			.containsExactly(expected.getId(), expected.getMake(), expected.getModel());
	}

	private void setupTestData() {
		var expectedCarEntities = List.of(
			new CarEntity(null, Make.FORD, "F150"),
			new CarEntity(null, Make.HONDA, "CIVIC")
		);

		var expectedUserEntities = List.of(
			new UserEntity(null, "Eric"),
			new UserEntity(null, "Jim"),
			new UserEntity(null, "Erin")
		);

		this.carEntityRepository.persist(expectedCarEntities);
		this.userEntityRepository.persist(expectedUserEntities);

		this.userAndCarEntityRepository.persist(
			Stream.of(
					new UserCarKey(expectedUserEntities.get(1).getId(), expectedCarEntities.get(0).getId()),
					new UserCarKey(expectedUserEntities.get(2).getId(), expectedCarEntities.get(0).getId()),
					new UserCarKey(expectedUserEntities.get(2).getId(), expectedCarEntities.get(1).getId())
				)
				.map(UserAndCarEntity::new)
		);

		this.expectedUsers = List.of(
			new UserDto(expectedUserEntities.get(0).getId(), expectedUserEntities.get(0).getName()),
			new UserDto(expectedUserEntities.get(1).getId(), expectedUserEntities.get(1).getName(), List.of(new CarDto(expectedCarEntities.get(0).getId(), expectedCarEntities.get(0).getMake(), expectedCarEntities.get(0).getModel()))),
			new UserDto(expectedUserEntities.get(2).getId(), expectedUserEntities.get(2).getName(), List.of(new CarDto(expectedCarEntities.get(0).getId(), expectedCarEntities.get(0).getMake(), expectedCarEntities.get(0).getModel()), new CarDto(expectedCarEntities.get(1).getId(), expectedCarEntities.get(1).getMake(), expectedCarEntities.get(1).getModel())))
		);

		this.expectedUsersMap = this.expectedUsers.stream()
			.collect(Collectors.toMap(UserDto::getId, Function.identity()));
	}
}
