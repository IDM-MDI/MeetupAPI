package com.modsen.meetup.api.validator;

import com.modsen.meetup.api.dto.EventDto;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.modsen.meetup.api.validator.ManagerValidator.isManagerValid;
import static com.modsen.meetup.api.validator.ObjectValidator.isStringEmpty;
import static com.modsen.meetup.api.validator.VenueValidator.isVenueValid;

public class EventValidator {
    private EventValidator() {}

    public static boolean isEventDtoValid(EventDto event) {
        return Objects.nonNull(event) &&
                isVenueValid(event.getVenue()) &&
                isManagerValid(event.getManager()) &&
                isEventInsideValid(event);
    }

    private static boolean isEventInsideValid(EventDto event) {
        return isTopicValid(event.getTopic()) && isDateValid(event.getDate());
    }

    private static boolean isTopicValid(String topic) {
        return !isStringEmpty(topic) && topic.length() < 255;
    }

    private static boolean isDateValid(LocalDateTime time) {
        return Objects.nonNull(time) && time.isAfter(LocalDateTime.now());
    }
}
