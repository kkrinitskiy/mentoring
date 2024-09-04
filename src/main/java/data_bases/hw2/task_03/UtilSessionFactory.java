package data_bases.hw2.task_03;

import data_bases.hw2.task_03.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilSessionFactory {
    private static SessionFactory sessionFactory;
    private UtilSessionFactory() {};
    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Car.class)
                    .addAnnotatedClass(TypesOfBodies.class)
                    .addAnnotatedClass(Coal.class)
                    .addAnnotatedClass(ShippedGoods.class)
                    .addAnnotatedClass(Wood.class)
                    .buildSessionFactory();
            return sessionFactory;
        }else {
            return sessionFactory;
        }
    }
}
