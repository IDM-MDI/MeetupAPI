package com.modsen.meetup.api.repository.query;

import static com.modsen.meetup.api.entity.EntityStatus.DELETED;

public class EventQuery {
    private EventQuery() {}
    public static final String FIND_BY_STATUS_QUERY = "SELECT e FROM Event e " +
            "WHERE status = :status AND e.topic = :filter " +
            "ORDER BY :sort";
    public static final String DELETE_QUERY = "UPDATE Event " +
            "SET status = " + DELETED.name() +
            " WHERE id = :id";
}
