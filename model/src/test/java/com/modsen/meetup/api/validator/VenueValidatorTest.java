package com.modsen.meetup.api.validator;

import com.modsen.meetup.api.dto.VenueDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VenueValidatorTest {
    private static final VenueDto EMPTY_VENUE = VenueDto.builder().build();
    private static final VenueDto TEST_VENUE
            = VenueDto.builder()
            .name("TEST VALUE")
            .build();

    @Test
    void isVenueValidShouldTrue() {
        Assertions.assertTrue(VenueValidator.isVenueValid(TEST_VENUE));
    }
    @Test
    void isVenueValidShouldFalse() {
        Assertions.assertFalse(VenueValidator.isVenueValid(EMPTY_VENUE));
    }

    @Test
    void isVenueValidShouldFalseByNull() {
        Assertions.assertFalse(VenueValidator.isVenueValid(null));
    }
}