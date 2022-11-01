package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.ManagerDto;

public interface VenueService {
    String findByID(long id);
    boolean isVenueExistByID(long id);
    boolean isVenueExistByName(String name);
    void save(ManagerDto manager);
}
