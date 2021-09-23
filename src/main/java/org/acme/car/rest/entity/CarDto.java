package org.acme.car.rest.entity;

import java.util.List;
import java.util.StringJoiner;

import org.acme.domain.Make;
import org.acme.user.rest.entity.UserDto;

public class CarDto {
	private Long id;
	private Make make;
	private String model;
	private List<UserDto> users;

	public CarDto() {
	}

	public CarDto(Long id, Make make, String model) {
		this(id, make, model, List.of());
	}

	public CarDto(Long id, Make make, String model, List<UserDto> users) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.users = users;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Make getMake() {
		return this.make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<UserDto> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CarDto.class.getSimpleName() + "[", "]").add("id=" + this.id).add("make=" + this.make).add("model='" + this.model + "'").add("users=" + this.users).toString();
	}
}
