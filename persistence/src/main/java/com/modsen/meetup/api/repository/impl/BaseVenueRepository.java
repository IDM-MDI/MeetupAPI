package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.entity.Venue;
import com.modsen.meetup.api.repository.VenueRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.modsen.meetup.api.repository.query.EntityQuery.ID;
import static com.modsen.meetup.api.repository.query.EntityQuery.NAME;
import static com.modsen.meetup.api.repository.query.VenueQuery.FIND_BY_ID;
import static com.modsen.meetup.api.repository.query.VenueQuery.FIND_BY_NAME;

@Repository
public class BaseVenueRepository implements VenueRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BaseVenueRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Venue findByID(long id) {
        try (Session session = sessionFactory.openSession()) {
            return (Venue) session.createQuery(FIND_BY_ID)
                    .setParameter(ID, id)
                    .getSingleResult();
        }
    }

    @Override
    public Venue findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return (Venue) session.createQuery(FIND_BY_NAME)
                    .setParameter(NAME, name)
                    .getSingleResult();
        }
    }

    @Override
    public boolean isVenueExistByID(long id) {
        return Objects.nonNull(findByID(id));
    }

    @Override
    public boolean isVenueExistByName(String name) {
        return Objects.nonNull(findByName(name));
    }

    @Override
    public Venue save(Venue venue) {
        try (Session session = sessionFactory.openSession()) {
            return (Venue) session.save(venue);
        }
    }
}
