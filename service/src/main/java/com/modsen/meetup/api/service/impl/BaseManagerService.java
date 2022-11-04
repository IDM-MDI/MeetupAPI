package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.repository.ManagerRepository;
import com.modsen.meetup.api.service.ManagerService;
import com.modsen.meetup.api.util.impl.ManagerModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.modsen.meetup.api.exception.ServiceExceptionCode.ENTITY_NOT_FOUND;
import static com.modsen.meetup.api.exception.ServiceExceptionCode.ENTITY_NOT_VALID;
import static com.modsen.meetup.api.validator.ManagerValidator.isManagerValid;

@Service
public class BaseManagerService implements ManagerService {
    private static final String SPACE = " ";
    private final ManagerRepository repository;
    private final ManagerModelMapper managerMapper;

    @Autowired
    public BaseManagerService(ManagerRepository repository, ManagerModelMapper managerMapper) {
        this.repository = repository;
        this.managerMapper = managerMapper;
    }

    @Override
    public ManagerDto findByID(long id) throws ServiceException {
        return managerMapper.toDto(
                repository.findByID(id)
                        .orElseThrow(() -> new ServiceException(ENTITY_NOT_FOUND.toString()))
        );
    }

    @Override
    public ManagerDto findByFullName(ManagerDto manager) throws ServiceException {
        String fullName = manager.getName() + SPACE + manager.getSurname() + SPACE + manager.getLastname();
        return managerMapper.toDto(
                repository.findByFullName(fullName)
                        .orElseThrow(() -> new ServiceException(ENTITY_NOT_FOUND.toString()))
        );
    }

    @Override
    public boolean isManagerExistByID(long id) {
        return repository.isManagerExistByID(id);
    }

    @Override
    public boolean isManagerExistByFullName(ManagerDto manager) {
        String fullName = manager.getName() + SPACE + manager.getSurname() + SPACE + manager.getLastname();
        return repository.isManagerExistByFullName(fullName);
    }

    @Override
    public ManagerDto save(ManagerDto manager) throws ServiceException {
        if(!isManagerValid(manager)) {
            throw new ServiceException(ENTITY_NOT_VALID.toString());
        }
        if(isManagerExistByFullName(manager)) {
            return findByFullName(manager);
        }
        return managerMapper.toDto(repository.save(managerMapper.toEntity(manager)));
    }
}
