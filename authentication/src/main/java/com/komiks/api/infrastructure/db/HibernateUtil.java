package com.komiks.api.infrastructure.db;

import com.komiks.api.infrastructure.db.model.User;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 * Configuration class for the hibernate properties.
 */
public class HibernateUtil {

    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    /**
     * Get the session factory singleton.
     *
     * @return the session factory.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties props = new Properties();
                props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                props.put(Environment.URL, System.getenv("DB_URL"));
                props.put(Environment.USER, System.getenv("DB_USER"));
                props.put(Environment.PASS, System.getenv("DB_PASS"));
                props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                // Use only for dev, we may need to use flyway or liquibase in the future.
                props.put(Environment.HBM2DDL_AUTO, "update");

                Configuration configuration = new Configuration();
                configuration.setProperties(props);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception exception) {
                logger.error(exception);
            }
        }

        return sessionFactory;
    }

}
