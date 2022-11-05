package com.modsen.meetup.api.util;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.entity.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.modsen.meetup.api.entity.EntityStatus.ACTIVE;
import static com.modsen.meetup.api.util.EventUtil.uniteUpdatableEvent;
import static org.junit.jupiter.api.Assertions.*;

class EventUtilTest {
    private static final String TEST = "TEST";
    private static final Event EXPECTED =
            Event.builder()
                    .id(1)
                    .topic(TEST)
                    .description(TEST + TEST + TEST)
                    .status(ACTIVE.name())
                    .date(LocalDateTime.of(2023,12,1,12,59))
                    .build();
    private static final Event UPDATABLE =
            Event.builder()
                    .topic(TEST)
                    .date(LocalDateTime.of(2023,12,1,12,59))
                    .build();
    private static final Event FROM_DB =
            Event.builder()
                    .id(1)
                    .description(TEST + TEST + TEST)
                    .date(LocalDateTime.MIN)
                    .status(ACTIVE.name())
                    .build();

    @Test
    void uniteUpdatableEventShouldCorrect() {
        Assertions.assertEquals(EXPECTED,uniteUpdatableEvent(UPDATABLE,FROM_DB));
    }
}