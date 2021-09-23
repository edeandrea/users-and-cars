package org.acme.persistence.entity;

import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_and_cars")
public class UserAndCarEntity {
	@EmbeddedId
	private UserCarKey id;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("userId")
//	@JoinColumn(name = "user_id")
//	private UserEntity user;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("carId")
//	@JoinColumn(name = "car_id")
//	private CarEntity car;

	public UserAndCarEntity() {
	}

	public UserAndCarEntity(Long userId, Long carId) {
		this(new UserCarKey(userId, carId));
	}

	public UserAndCarEntity(UserCarKey id) {
		this.id = id;
//		this(id, null, null);
	}

//	public UserAndCarEntity(UserCarKey id, UserEntity user, CarEntity car) {
//		this.id = id;
//		this.user = user;
//		this.car = car;
//	}

	public UserCarKey getId() {
		return this.id;
	}

	public void setId(UserCarKey id) {
		this.id = id;
	}

//	public UserEntity getUser() {
//		return this.user;
//	}
//
//	public void setUser(UserEntity user) {
//		this.user = user;
//	}
//
//	public CarEntity getCar() {
//		return this.car;
//	}
//
//	public void setCar(CarEntity car) {
//		this.car = car;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserAndCarEntity that = (UserAndCarEntity) o;
		return this.id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UserAndCarEntity.class.getSimpleName() + "[", "]")
			.add("id=" + this.id)
//			.add("user=" + this.user)
//			.add("car=" + this.car)
			.toString();
	}
}
