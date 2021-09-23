package org.acme.persistence;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.persistence.entity.UserAndCarEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserAndCarEntityRepository implements PanacheRepository<UserAndCarEntity> {
	public List<UserAndCarEntity> listAllForUserId(Long userId) {
		return list("user_id", userId);
	}

	public List<UserAndCarEntity> listAllForCarId(Long carId) {
		return list("car_id", carId);
	}
}
