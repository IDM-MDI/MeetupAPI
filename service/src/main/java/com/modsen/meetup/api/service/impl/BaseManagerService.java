package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.exception.PersistenceException;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.repository.ManagerRepository;
import com.modsen.meetup.api.service.ManagerService;
import com.modsen.meetup.api.util.impl.ManagerModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.modsen.meetup.api.exception.ServiceExceptionCode.ENTITY_NOT_FOUND;
import static com.modsen.meetup.api.exception.ServiceExceptionCode.ENTITY_NOT_VALID;
import static com.modsen.meetup.api.util.ManagerUtil.getFullName;
import static com.modsen.meetup.api.validator.ManagerValidator.isManagerValid;

@Service
@Slf4j
public class BaseManagerService implements ManagerService {

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
                        .orElseThrow(() -> {
                            log.error(ENTITY_NOT_FOUND.toString());
                            return new ServiceException(ENTITY_NOT_FOUND.toString());
                        })
        );
    }

    @Override
    public ManagerDto findByFullName(ManagerDto manager) throws ServiceException {
        return managerMapper.toDto(
                repository.findByFullName(getFullName(manager))
                        .orElseThrow(() -> {
                            log.error(ENTITY_NOT_FOUND.toString());
                            return new ServiceException(ENTITY_NOT_FOUND.toString());
                        })
        );
    }

    @Override
    public boolean isManagerExistByID(long id) {
        return repository.isManagerExistByID(id);
    }

    @Override
    public boolean isManagerExistByFullName(ManagerDto manager) {
        return repository.isManagerExistByFullName(getFullName(manager));
    }

    @Override
    public ManagerDto save(ManagerDto manager) throws ServiceException, PersistenceException {
        if(!isManagerValid(manager)) {
            log.error(ENTITY_NOT_VALID.toString());
            throw new ServiceException(ENTITY_NOT_VALID.toString());
        }
        if(isManagerExistByFullName(manager)) {
            return findByFullName(manager);
        }
        Manager result = repository.save(managerMapper.toEntity(manager));
        log.info("Manager {} was saved", result);
        return managerMapper.toDto(result);
    }
}
