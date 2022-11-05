package com.modsen.meetup.api.validator;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.dto.VenueDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.modsen.meetup.api.entity.EntityStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventValidatorTest {
    private static final ManagerDto MANAGER = ManagerDto.builder().build();
    private static final VenueDto VENUE = VenueDto.builder().build();
    private static final EventDto EVENT = EventDto.builder()
            .id(1L)
            .topic("Topic")
            .description("This is description")
            .manager(MANAGER)
            .venue(VENUE)
            .date(LocalDateTime.MAX)
            .status(ACTIVE.name())
            .build();
    @Test
    void isEventDtoValidShouldTrue() {
        try (MockedStatic<VenueValidator> validatorMockedStatic = mockStatic(VenueValidator.class);
            MockedStatic<ManagerValidator> managerValidatorMocked = mockStatic(ManagerValidator.class)) {
            when(VenueValidator.isVenueValid(VENUE))
                    .thenReturn(true);
            when(ManagerValidator.isManagerValid(MANAGER))
                    .thenReturn(true);
            Assertions.assertTrue(EventValidator.isEventDtoValid(EVENT));
        }
    }

    @Test
    void isEventDtoValidShouldFalseByNull() {
        Assertions.assertFalse(EventValidator.isEventDtoValid(null));
    }

    @Test
    void isEventDtoValidShouldFalse() {
        Assertions.assertFalse(EventValidator.isEventDtoValid(EventDto.builder().build()));
    }
}