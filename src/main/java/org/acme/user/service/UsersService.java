package org.acme.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

import org.acme.car.mapping.CarMapper;
import org.acme.car.persistence.CarEntity;
import org.acme.car.persistence.CarEntityRepository;
import org.acme.car.rest.CarDto;
import org.acme.common.persistence.UserAndCarEntity;
import org.acme.common.persistence.UserAndCarEntityRepository;
import org.acme.common.persistence.UserCarKey;
import org.acme.user.mapping.UserMapper;
import org.acme.user.persistence.UserEntity;
import org.acme.user.persistence.UserEntityRepository;
import org.acme.user.rest.UserDto;

@ApplicationScoped
public class UsersService {
	private final UserEntityRepository userEntityRepository;
	private final CarEntityRepository carEntityRepository;
	private final UserAndCarEntityRepository userAndCarEntityRepository;
	private final UserMapper userMapper;
	private final CarMapper carMapper;

	public UsersService(UserEntityRepository userEntityRepository, CarEntityRepository carEntityRepository, UserAndCarEntityRepository userAndCarEntityRepository, UserMapper userMapper, CarMapper carMapper) {
		this.userEntityRepository = userEntityRepository;
		this.carEntityRepository = carEntityRepository;
		this.userAndCarEntityRepository = userAndCarEntityRepository;
		this.userMapper = userMapper;
		this.carMapper = carMapper;
	}

	public List<UserDto> getAllUsers() {
		return this.userEntityRepository.listAll().stream()
			.map(this::createUser)
			.collect(Collectors.toList());
	}

	public Optional<UserDto> findUser(Long userId) {
		return this.userEntityRepository.findByIdOptional(userId)
			.map(this::createUser);
	}

	private UserDto createUser(UserEntity userEntity) {
//		Stream<Long> carIdsForUser = Optional.ofNullable(userEntity)
//			.map(UserEntity::getUsersAndCars)
//			.orElseGet(Set::of)
		Stream<Long> carIdsForUser = this.userAndCarEntityRepository.listAllForUserId(userEntity.getId())
			.stream()
			.map(UserAndCarEntity::getId)
			.map(UserCarKey::getCarId)
			.distinct();

		Stream<CarEntity> carEntitiesForUser = carIdsForUser
			.map(this.carEntityRepository::findByIdOptional)
			.filter(Optional::isPresent)
			.map(Optional::get);

		List<CarDto> carDtos = carEntitiesForUser
			.map(this.carMapper::carEntityToCarDto)
			.collect(Collectors.toList());

		var userDto = this.userMapper.userEntityToUserDto(userEntity);
		userDto.setCars(carDtos);

		return userDto;
	}
}
