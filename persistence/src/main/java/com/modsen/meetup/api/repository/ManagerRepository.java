package com.modsen.meetup.api.repository;

import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.exception.PersistenceException;

import java.util.Optional;

public interface ManagerRepository {
    Optional<Manager> findByID(long id);
    Optional<Manager> findByFullName(String fullName);
    boolean isManagerExistByFullName(String fullName);
    boolean isManagerExistByID(long id);
    Manager save(Manager manager) throws PersistenceException;
}
