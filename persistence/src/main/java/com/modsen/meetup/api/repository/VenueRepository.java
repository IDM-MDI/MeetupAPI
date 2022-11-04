package com.modsen.meetup.api.repository;

import com.modsen.meetup.api.entity.Venue;
import com.modsen.meetup.api.exception.PersistenceException;

import java.util.Optional;

public interface VenueRepository {
    Optional<Venue> findByID(long id);
    Optional<Venue> findByName(String name);
    boolean isVenueExistByID(long id);
    boolean isVenueExistByName(String name);
    Venue save(Venue venue) throws PersistenceException;
}
