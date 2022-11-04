package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.repository.ManagerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.Objects;
import java.util.Optional;

import static com.modsen.meetup.api.exception.PersistenceCode.ERROR_WHILE_SAVE;
import static com.modsen.meetup.api.repository.query.EntityQuery.ID;
import static com.modsen.meetup.api.repository.query.ManagerQuery.*;

@Repository
public class BaseManagerRepository implements ManagerRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BaseManagerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Manager> findByID(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of((Manager) session.createQuery(FIND_BY_ID)
                    .setParameter(ID, id)
                    .getSingleResult());
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Manager> findByFullName(String fullName) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of((Manager) session.createQuery(FIND_BY_FULL_NAME)
                    .setParameter(FULL_NAME, fullName)
                    .getSingleResult());
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isManagerExistByFullName(String fullName) {
        return findByFullName(fullName).isPresent();
    }

    @Override
    public boolean isManagerExistByID(long id) {
        return findByID(id).isPresent();
    }

    @Override
    public Manager save(Manager manager) {
        try (Session session = sessionFactory.openSession()) {
            Long id = (Long) session.save(manager);
            return findByID(id)
                    .orElseThrow(() -> new PersistenceException(ERROR_WHILE_SAVE.toString()));
        }
    }
}
