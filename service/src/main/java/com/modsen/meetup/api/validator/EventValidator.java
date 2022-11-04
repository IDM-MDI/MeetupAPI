package com.modsen.meetup.api.validator;

import com.modsen.meetup.api.dto.EventDto;

public class EventValidator {
    private EventValidator() {}

    public static boolean isEventDtoValid(EventDto event) {
        return true;
    }   //TODO:FINISH VALIDATOR
}
