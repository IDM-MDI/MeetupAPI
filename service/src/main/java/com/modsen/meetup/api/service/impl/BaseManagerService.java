package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.service.ManagerService;
import org.springframework.stereotype.Service;

@Service
public class BaseManagerService implements ManagerService {
    @Override
    public ManagerDto findByID(long id) {
        return null;
    }

    @Override
    public ManagerDto findByFullName(String name, String surname, String lastname) {
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
    public ManagerDto save(ManagerDto manager) {
        return null;
    }
}
