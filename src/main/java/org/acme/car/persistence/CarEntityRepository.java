package org.acme.car.persistence;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CarEntityRepository implements PanacheRepository<CarEntity> {
}
