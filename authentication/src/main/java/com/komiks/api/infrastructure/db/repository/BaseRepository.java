package com.komiks.api.infrastructure.db.repository;

import com.komiks.api.infrastructure.db.HibernateUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;

/**
 * Base implementation for repository.
 */
public abstract class BaseRepository {

    protected EntityManager getEntityManager() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return entityManager;
    }
}
