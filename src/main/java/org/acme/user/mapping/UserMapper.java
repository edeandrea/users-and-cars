package org.acme.user.mapping;

import org.acme.user.persistence.UserEntity;
import org.acme.user.rest.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
	@Mapping(target = "cars", ignore = true)
	UserDto userEntityToUserDto(UserEntity user);

	@InheritInverseConfiguration
	UserEntity userDtoToUserEntity(UserDto user);
}
