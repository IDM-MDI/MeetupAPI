package com.modsen.meetup.api.repository;

import com.modsen.meetup.api.entity.Manager;

public interface ManagerRepository {
    Manager findByID(long id);
    Manager findByFullName(Manager manager);
    boolean isManagerExistByFullName(Manager manager);
    boolean isManagerExistByID(long id);
    Manager save(Manager manager);
}
