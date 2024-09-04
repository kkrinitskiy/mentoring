package data_bases.hw2.task_02;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilSessionFactory {
    private static SessionFactory sessionFactory;

    private UtilSessionFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(History.class)
                    .addAnnotatedClass(Product.class)
                    .buildSessionFactory();
            return sessionFactory;
        }else {
            return sessionFactory;
        }
    }
}
