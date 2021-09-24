package org.acme.user.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.acme.user.persistence.UserEntity;
import org.acme.user.rest.UserDto;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class UserMapperTests {
	@Inject
	UserMapper userMapper;

	@Test
	public void mapsUserWithoutCars() {
		var userDto = this.userMapper.userEntityToUserDto(new UserEntity(1L, "Eric"));

		assertThat(userDto)
			.isNotNull()
			.extracting(
				UserDto::getId,
				UserDto::getName
			)
			.containsExactly(
				1L,
				"Eric"
			);

		assertThat(userDto.getCars())
			.isNull();
	}
}
