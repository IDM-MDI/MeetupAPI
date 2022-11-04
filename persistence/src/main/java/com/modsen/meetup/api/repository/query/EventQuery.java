package com.modsen.meetup.api.repository.query;


public class EventQuery {
    private EventQuery() {}
    public static final String FIND_BY_ID_QUERY = "SELECT e FROM Event e " +
            "WHERE e.id = :id";
    public static final String FIND_BY_FILTER_AND_STATUS_QUERY = "FROM Event e " +
            "WHERE e.status = :status AND e.topic LIKE CONCAT('%',:filter,'%') " +
            "ORDER BY :sort";
    public static final String FIND_BY_STATUS_QUERY = "FROM Event e " +
            "WHERE e.status = :status " +
            "ORDER BY :sort";
    public static final String DELETE_QUERY = "UPDATE Event e " +
            "SET e.status = :status" +
            " WHERE e.id = :id";
}
