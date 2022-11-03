package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.repository.EventRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.modsen.meetup.api.repository.query.EntityQuery.*;
import static com.modsen.meetup.api.repository.query.EventQuery.*;

@Repository
public class BaseEventRepository implements EventRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BaseEventRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Event findByID(long id) {
        try (Session session = sessionFactory.openSession()) {
            return (Event) session.createQuery(FIND_BY_ID_QUERY)
                    .setParameter(ID, id)
                    .getSingleResult();
        }
    }

    @Override
    public boolean isEventExistByID(long id) {
        return Objects.nonNull(findByID(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> findEventsByStatus(PaginationInfo pagination, String status) {
        try (Session session = sessionFactory.openSession()) {
            return ((List<Event>) session.createQuery(FIND_BY_STATUS_QUERY)
                    .setParameter(STATUS, status)
                    .setParameter(FILTER, pagination.getFilter())
                    .setParameter(SORT, pagination.getSort())
                    .setMaxResults((int) pagination.getSize())
                    .setFirstResult((int) pagination.getPage())
                    .list());
        }
    }

    @Override
    public Event save(Event event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Event savedEvent = (Event) session.save(event);
            session.getTransaction().commit();
            return savedEvent;
        }
    }

    @Override
    public Event update(Event event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
        }
        return findByID(event.getId());
    }

    @Override
    public Event delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.createQuery(DELETE_QUERY)
                    .setParameter(ID, id)
                    .executeUpdate();
        }
        return findByID(id);
    }
}
