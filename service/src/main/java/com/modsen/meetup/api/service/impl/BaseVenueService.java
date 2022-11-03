package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.VenueDto;
import com.modsen.meetup.api.service.VenueService;
import org.springframework.stereotype.Service;

@Service
public class BaseVenueService implements VenueService {
    @Override
    public VenueDto findByID(long id) {
        return null;
    }

    @Override
    public boolean isVenueExistByID(long id) {
        return false;
    }

    @Override
    public boolean isVenueExistByName(String name) {
        return false;
    }

    @Override
    public VenueDto save(VenueDto venue) {
        return null;
    }
}
