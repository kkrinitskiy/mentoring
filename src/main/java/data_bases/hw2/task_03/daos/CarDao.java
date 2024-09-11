package data_bases.hw2.task_03.daos;

import data_bases.hw2.task_03.entities.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao implements ParentDao<Car> {

    @Override
    public List<Car> getAll() {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            List<Car> cars = session.createQuery("from Car", Car.class).list();
            tx.commit();
            return cars;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public boolean add(Car car) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.persist(car);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Car car) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.remove(car);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Car car) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.merge(car);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}

