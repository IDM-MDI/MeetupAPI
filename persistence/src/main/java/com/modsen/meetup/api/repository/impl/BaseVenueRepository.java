package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.entity.Venue;
import com.modsen.meetup.api.repository.VenueRepository;

public class BaseVenueRepository implements VenueRepository {
    @Override
    public Venue findByID(long id) {
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
    public Venue save(Venue venue) {
        return null;
    }
}
