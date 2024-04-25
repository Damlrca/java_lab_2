package org.example.java_lab;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Lab3_HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public Lab3_HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("/lab3_hibernate.cfg.xml");
                configuration.addAnnotatedClass(Lab3_TablePlayer.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Exception! " + e);
            }
        }
        return sessionFactory;
    }
}
