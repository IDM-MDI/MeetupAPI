package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.ManagerDto;

public interface ManagerService {
    ManagerDto findByID(long id);
    ManagerDto findByFullName(String name, String surname, String lastname);
    boolean isManagerExistByID(long id);
    boolean isManagerExistByName(String name);
    ManagerDto save(ManagerDto manager);
}
