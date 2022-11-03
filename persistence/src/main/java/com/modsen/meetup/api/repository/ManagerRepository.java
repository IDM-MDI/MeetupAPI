package com.modsen.meetup.api.repository;

import com.modsen.meetup.api.entity.Manager;

public interface ManagerRepository {
    Manager findByID(long id);
    Manager findByFullName(String name, String surname, String lastname);
    boolean isManagerExistByID(long id);
    boolean isManagerExistByName(String name);
    Manager save(Manager manager);
}
