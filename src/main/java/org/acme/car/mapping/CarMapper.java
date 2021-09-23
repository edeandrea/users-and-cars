package org.acme.car.mapping;

import org.acme.car.persistence.entity.CarEntity;
import org.acme.car.rest.entity.CarDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {
	@Mapping(target = "users", ignore = true)
	CarDto carEntityToCarDto(CarEntity car);

	@InheritInverseConfiguration
	CarEntity carDtoToCarEntity(CarDto car);
}
