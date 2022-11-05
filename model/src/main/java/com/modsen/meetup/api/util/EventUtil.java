package com.modsen.meetup.api.util;

import com.modsen.meetup.api.entity.Event;

import static com.modsen.meetup.api.validator.EventValidator.isDateValid;
import static com.modsen.meetup.api.validator.ObjectValidator.isNumberEqLessZero;
import static com.modsen.meetup.api.validator.ObjectValidator.isStringEmpty;

public class EventUtil {
    private EventUtil() {}

    public static Event uniteUpdatableEvent(Event updatable, Event other) {
        return Event.builder()
                .id(isNumberEqLessZero(updatable.getId()) ? other.getId() : updatable.getId())
                .topic(isStringEmpty(updatable.getTopic()) ? other.getTopic() : updatable.getTopic())
                .date(isDateValid(updatable.getDate()) ? updatable.getDate() : other.getDate())
                .description(isStringEmpty(updatable.getDescription()) ? other.getDescription() : updatable.getDescription())
                .manager(updatable.getManager())
                .venue(updatable.getVenue())
                .status(other.getStatus())
                .build();
    }
}
