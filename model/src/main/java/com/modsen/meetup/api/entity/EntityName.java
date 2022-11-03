package com.modsen.meetup.api.entity;

public enum EntityName {
    EVENT, MANAGER, VENUE;

    @Override
    public String toString() {
        return super.name().toLowerCase();
    }
}
