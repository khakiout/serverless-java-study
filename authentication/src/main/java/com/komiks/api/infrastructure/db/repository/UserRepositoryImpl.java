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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
            logger.info("User saved.");
        } catch (PersistenceException e) {
            logger.error(e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public User getUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
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
