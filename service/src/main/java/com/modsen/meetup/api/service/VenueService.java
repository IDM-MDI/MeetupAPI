package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.VenueDto;
import com.modsen.meetup.api.exception.PersistenceException;
import com.modsen.meetup.api.exception.ServiceException;

public interface VenueService {
    VenueDto findByID(long id) throws ServiceException;
    VenueDto findByName(String name);
    boolean isVenueExistByID(long id);
    boolean isVenueExistByName(String name);
    VenueDto save(VenueDto venue) throws ServiceException, PersistenceException;
}
