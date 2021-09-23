package org.acme.user.persistence;

import javax.enterprise.context.ApplicationScoped;

import org.acme.user.persistence.entity.UserEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserEntityRepository implements PanacheRepository<UserEntity> {
}
