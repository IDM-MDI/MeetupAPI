package com.modsen.meetup.api.repository.impl;

import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.repository.ManagerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

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
    public Manager findByID(long id) {
        try (Session session = sessionFactory.openSession()) {
            return (Manager) session.createQuery(FIND_BY_ID)
                    .setParameter(ID, id)
                    .getSingleResult();
        }
    }

    @Override
    public Manager findByFullName(Manager manager) {
        try (Session session = sessionFactory.openSession()) {
            return (Manager) session.createQuery(FIND_BY_FULL_NAME)
                    .setParameter(FULL_NAME, manager.getFullName())
                    .getSingleResult();
        }
    }

    @Override
    public boolean isManagerExistByFullName(Manager manager) {
        return Objects.nonNull(findByFullName(manager));
    }

    @Override
    public boolean isManagerExistByID(long id) {
        return Objects.nonNull(findByID(id));
    }

    @Override
    public Manager save(Manager manager) {
        try (Session session = sessionFactory.openSession()) {
            return (Manager) session.save(manager);
        }
    }
}
