package com.modsen.meetup.api.repository;

import com.modsen.meetup.api.entity.Venue;

public interface VenueRepository {
    Venue findByID(long id);
    boolean isVenueExistByID(long id);
    boolean isVenueExistByName(String name);
    Venue save(Venue venue);
}
