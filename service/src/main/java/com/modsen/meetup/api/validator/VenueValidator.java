package com.modsen.meetup.api.validator;

import com.modsen.meetup.api.dto.VenueDto;

import static com.modsen.meetup.api.validator.ObjectValidator.isStringEmpty;

public class VenueValidator {
    private VenueValidator() {}

    public static boolean isVenueValid(VenueDto venue) {
        return !isStringEmpty(venue.getName()) && venue.getName().length() < 255;
    }
}
