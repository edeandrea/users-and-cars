package org.acme.user.persistence.entity;

import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "User must have a name")
	private String name;

//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//	private Set<UserAndCarEntity> usersAndCars;

	public UserEntity() {
	}

	public UserEntity(Long id, String name) {
		this.id = id;
		this.name = name;
//		this(id, name, Set.of());
	}

//	public UserEntity(Long id, String name, Set<UserAndCarEntity> usersAndCars) {
//		this.id = id;
//		this.name = name;
//		this.usersAndCars = usersAndCars;
//	}

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
		UserEntity user = (UserEntity) o;
		return this.id.equals(user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UserEntity.class.getSimpleName() + "[", "]")
			.add("id=" + this.id)
			.add("name='" + this.name + "'")
//			.add("usersAndCars=" + this.usersAndCars)
			.toString();
	}
}
