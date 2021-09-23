package org.acme.car.persistence.entity;

import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.acme.domain.Make;

@Entity
@Table(name = "cars")
public class CarEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Car must have a make")
	private Make make;

	@NotEmpty(message = "Car must have a model")
	private String model;

//	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
//	private Set<UserAndCarEntity> usersAndCars;

	public CarEntity() {
	}

	public CarEntity(Long id, Make make, String model) {
//		this(id, make, model, Set.of());
		this.id = id;
		this.make = make;
		this.model = model;
	}

//	public CarEntity(Long id, Make make, String model, Set<UserAndCarEntity> usersAndCars) {
//		this.id = id;
//		this.make = make;
//		this.model = model;
//		this.usersAndCars = usersAndCars;
//	}

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

//	public Set<UserAndCarEntity> getUsersAndCars() {
//		return this.usersAndCars;
//	}
//
//	public void setUsersAndCars(Set<UserAndCarEntity> usersAndCars) {
//		this.usersAndCars = usersAndCars;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CarEntity car = (CarEntity) o;
		return this.id.equals(car.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CarEntity.class.getSimpleName() + "[", "]")
			.add("id=" + this.id)
			.add("make=" + this.make)
			.add("model='" + this.model + "'")
//			.add("usersAndCars=" + this.usersAndCars)
			.toString();
	}
}
