package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.repository.EventRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

import static com.modsen.meetup.api.entity.EntityStatus.DELETED;
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
    public Optional<Event> findByID(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of((Event) session.createQuery(FIND_BY_ID_QUERY)
                    .setParameter(ID, id)
                    .getSingleResult());
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isEventExistByID(long id) {
        return findByID(id).isPresent();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> findEventsByStatus(PaginationInfo pagination, String status) {
        try (Session session = sessionFactory.openSession()) {
            return ((List<Event>) createPageQuery(pagination, status, session)
                    .getResultList());
        }
    }

    @Override
    public Event save(Event event) {
        Long id;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            id = (Long) session.save(event);
            session.getTransaction().commit();
        }
        return findByID(id)
                .orElseThrow(() -> new PersistenceException(""));
    }

    @Override
    public Event update(Event event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
        }
        return findByID(event.getId())
                .orElseThrow(() -> new PersistenceException(""));
    }

    @Override
    public Event delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery(DELETE_QUERY)
                    .setParameter(ID, id)
                    .setParameter(STATUS,DELETED.name())
                    .executeUpdate();
            session.getTransaction().commit();
        }
        return findByID(id)
                .orElseThrow(() -> new PersistenceException(""));
    }

    private Query createPageQuery(PaginationInfo pagination, String status, Session session) {
        boolean haveFilter = !pagination.getFilter().isBlank();
        String query = haveFilter ? FIND_BY_FILTER_AND_STATUS_QUERY : FIND_BY_STATUS_QUERY;
        Query sessionQuery = session.createQuery(query)
                .setParameter(STATUS, status)
                .setParameter(SORT, pagination.getSort());
        if(haveFilter) {
            sessionQuery.setParameter(FILTER, pagination.getFilter());
        }
        return sessionQuery
                .setMaxResults((int) pagination.getSize())
                .setFirstResult((int) pagination.getPage());
    }
}
