package com.modsen.meetup.api.repository.query;

public class VenueQuery {
    private VenueQuery() {}
    public static final String FIND_BY_ID = "SELECT v FROM Venue v " +
            "WHERE v.id = :id";
    public static final String FIND_BY_NAME = "SELECT v FROM Venue v " +
            "WHERE v.name = :name";
}
