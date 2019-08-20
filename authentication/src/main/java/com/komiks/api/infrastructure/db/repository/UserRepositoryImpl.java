package com.komiks.api.infrastructure.db.repository;

import com.komiks.api.infrastructure.db.HibernateUtil;
import com.komiks.api.infrastructure.db.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {

    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    @Override
    public void saveUser(User user) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            logger.error("Failed to save user.", pe);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User getUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User user = null;
        try {
            user = session.byNaturalId(User.class)
                .using("username", username)
                .load();
            tx.commit();
        } catch (RuntimeException e) {
            logger.error(e);
            tx.rollback();
        } finally {
            session.close();
        }

        return user;
    }

}
