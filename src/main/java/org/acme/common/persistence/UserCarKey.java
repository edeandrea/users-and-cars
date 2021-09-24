package org.acme.common.persistence;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserCarKey implements Serializable {
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "car_id")
	private Long carId;

	public UserCarKey() {
	}

	public UserCarKey(Long userId, Long carId) {
		this.userId = userId;
		this.carId = carId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserCarKey that = (UserCarKey) o;
		return this.userId.equals(that.userId) && this.carId.equals(that.carId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.userId, this.carId);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UserCarKey.class.getSimpleName() + "[", "]").add("userId=" + this.userId).add("carId=" + this.carId).toString();
	}
}
