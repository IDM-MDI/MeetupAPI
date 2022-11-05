package com.modsen.meetup.api.validator;

import com.modsen.meetup.api.dto.VenueDto;

import java.util.Objects;

import static com.modsen.meetup.api.validator.ObjectValidator.isStringEmpty;

public class VenueValidator {
    private VenueValidator() {}

    public static boolean isVenueValid(VenueDto venue) {
        return Objects.nonNull(venue) &&
                !isStringEmpty(venue.getName()) &&
                venue.getName().length() < 255;
    }
}
