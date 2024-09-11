package data_bases.hw2.task_03.daos;

import data_bases.hw2.task_03.entities.Coal;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CoalDao implements ParentDao<Coal> {

    @Override
    public List<Coal> getAll() {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            List<Coal> coal = session.createQuery("from Coal", Coal.class).list();
            tx.commit();
            return coal;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public boolean add(Coal coal) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.persist(coal);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Coal coal) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.remove(coal);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Coal coal) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.merge(coal);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}