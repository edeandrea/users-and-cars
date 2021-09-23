package org.acme.car.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.acme.car.persistence.entity.CarEntity;
import org.acme.car.rest.entity.CarDto;
import org.acme.domain.Make;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CarMapperTests {
	@Inject
	CarMapper carMapper;

	@Test
	public void mapsCarWithoutUsers() {
		var carDto = this.carMapper.carEntityToCarDto(new CarEntity(1L, Make.FORD, "F150"));

		assertThat(carDto)
			.isNotNull()
			.extracting(
				CarDto::getId,
				CarDto::getMake,
				CarDto::getModel
			)
			.containsExactly(
				1L,
				Make.FORD,
				"F150"
			);

		assertThat(carDto.getUsers())
			.isNull();
	}
}
