package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.VenueDto;
import com.modsen.meetup.api.entity.Venue;
import com.modsen.meetup.api.exception.PersistenceException;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.repository.VenueRepository;
import com.modsen.meetup.api.service.VenueService;
import com.modsen.meetup.api.util.impl.VenueModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.modsen.meetup.api.exception.ServiceExceptionCode.ENTITY_NOT_FOUND;
import static com.modsen.meetup.api.exception.ServiceExceptionCode.ENTITY_NOT_VALID;
import static com.modsen.meetup.api.validator.VenueValidator.isVenueValid;

@Service
@Slf4j
public class BaseVenueService implements VenueService {

    private final VenueRepository repository;
    private final VenueModelMapper venueMapper;

    @Autowired
    public BaseVenueService(VenueRepository repository, VenueModelMapper venueMapper) {
        this.repository = repository;
        this.venueMapper = venueMapper;
    }

    @Override
    public VenueDto findByID(long id) throws ServiceException {
        return venueMapper.toDto(
                repository.findByID(id)
                        .orElseThrow(() -> {
                            log.error(ENTITY_NOT_FOUND.toString());
                            return new ServiceException(ENTITY_NOT_FOUND.toString());
                        })
        );
    }

    @Override
    public boolean isVenueExistByID(long id) {
        return repository.isVenueExistByID(id);
    }

    @Override
    public boolean isVenueExistByName(String name) {
        return repository.isVenueExistByName(name);
    }

    @Override
    public VenueDto findByName(String name) throws ServiceException {
        return venueMapper.toDto(
                repository.findByName(name)
                        .orElseThrow(() -> {
                            log.error(ENTITY_NOT_FOUND.toString());
                            return new ServiceException(ENTITY_NOT_FOUND.toString());
                        })
        );
    }

    @Override
    public VenueDto save(VenueDto venue) throws ServiceException, PersistenceException {
        if(!isVenueValid(venue)) {
            log.error(ENTITY_NOT_VALID.toString());
            throw new ServiceException(ENTITY_NOT_VALID.toString());
        }
        if(isVenueExistByName(venue.getName())) {
            return findByName(venue.getName());
        }
        Venue result = repository.save(venueMapper.toEntity(venue));
        log.info("Venue {} was saved", result);
        return venueMapper.toDto(result);
    }
}
