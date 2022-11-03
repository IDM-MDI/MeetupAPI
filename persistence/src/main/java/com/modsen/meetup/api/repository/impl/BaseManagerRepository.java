package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.repository.ManagerRepository;

public class BaseManagerRepository implements ManagerRepository {
    @Override
    public Manager findByID(long id) {
        return null;
    }

    @Override
    public Manager findByFullName(String name, String surname, String lastname) {
        return null;
    }

    @Override
    public boolean isManagerExistByID(long id) {
        return false;
    }

    @Override
    public boolean isManagerExistByName(String name) {
        return false;
    }

    @Override
    public Manager save(Manager manager) {
        return null;
    }
}
