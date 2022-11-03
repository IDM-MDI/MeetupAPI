package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.VenueDto;

public interface VenueService {
    VenueDto findByID(long id);
    boolean isVenueExistByID(long id);
    boolean isVenueExistByName(String name);
    VenueDto save(VenueDto venue);
}
