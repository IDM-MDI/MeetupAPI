package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.exception.ServiceException;

public interface ManagerService {
    ManagerDto findByID(long id) throws ServiceException;
    ManagerDto findByFullName(ManagerDto manager) throws ServiceException;
    boolean isManagerExistByID(long id);
    boolean isManagerExistByFullName(ManagerDto manager);
    ManagerDto save(ManagerDto manager) throws ServiceException;
}
