package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.entity.Venue;
import com.modsen.meetup.api.repository.VenueRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.Optional;

import static com.modsen.meetup.api.exception.PersistenceCode.ERROR_WHILE_SAVE;
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
    public Optional<Venue> findByID(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of((Venue) session.createQuery(FIND_BY_ID)
                    .setParameter(ID, id)
                    .getSingleResult());
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Venue> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of((Venue) session.createQuery(FIND_BY_NAME)
                    .setParameter(NAME, name)
                    .getSingleResult());
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isVenueExistByID(long id) {
        return findByID(id).isPresent();
    }

    @Override
    public boolean isVenueExistByName(String name) {
        return findByName(name).isPresent();
    }

    @Override
    public Venue save(Venue venue) {
        Long id;
        try (Session session = sessionFactory.openSession()) {
            id = (Long) session.save(venue);
        }
        return findByID(id)
                .orElseThrow(() -> new PersistenceException(ERROR_WHILE_SAVE.toString()));
    }
}
