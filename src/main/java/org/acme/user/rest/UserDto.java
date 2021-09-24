package org.acme.user.rest;

import java.util.List;
import java.util.StringJoiner;

import org.acme.car.rest.CarDto;

public class UserDto {
	private Long id;
	private String name;
	private List<CarDto> cars;

	public UserDto() {
	}

	public UserDto(Long id, String name) {
		this(id, name, List.of());
	}

	public UserDto(Long id, String name, List<CarDto> cars) {
		this.id = id;
		this.name = name;
		this.cars = cars;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CarDto> getCars() {
		return this.cars;
	}

	public void setCars(List<CarDto> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UserDto.class.getSimpleName() + "[", "]").add("id=" + this.id).add("name='" + this.name + "'").add("cars=" + this.cars).toString();
	}
}
