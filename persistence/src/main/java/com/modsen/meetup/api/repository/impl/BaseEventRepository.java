package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.repository.EventRepository;

import javax.persistence.EntityManager;

public class BaseEventRepository implements EventRepository {
    private EntityManager entityManager;

}
