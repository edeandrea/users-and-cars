package org.acme.user.persistence;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserEntityRepository implements PanacheRepository<UserEntity> {
}
