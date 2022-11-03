package com.modsen.meetup.api.repository.query;

public class ManagerQuery {
    private ManagerQuery() {}
    public static final String FULL_NAME = "fullName";
    public static final String FIND_BY_FULL_NAME = "SELECT m FROM Manager m " +
            "WHERE m.fullName = :fullName";
    public static final String FIND_BY_ID = "SELECT m FROM Manager m " +
            "WHERE m.id = :id";
}
