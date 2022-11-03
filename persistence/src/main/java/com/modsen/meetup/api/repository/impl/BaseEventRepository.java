package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.repository.EventRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseEventRepository implements EventRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public BaseEventRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Event findByID(long id) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            return session.find(Event.class, id);
        }
    }

    @Override
    public boolean isEventExistByID(long id) {
        return false;
    }

    @Override
    public Event save(Event event) {
        return null;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public Event delete(long id) {
        return null;
    }
}
