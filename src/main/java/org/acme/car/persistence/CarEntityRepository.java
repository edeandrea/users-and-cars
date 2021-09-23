package org.acme.car.persistence;

import javax.enterprise.context.ApplicationScoped;

import org.acme.car.persistence.entity.CarEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CarEntityRepository implements PanacheRepository<CarEntity> {
}
