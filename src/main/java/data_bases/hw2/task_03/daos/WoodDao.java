package data_bases.hw2.task_03.daos;

import data_bases.hw2.task_03.entities.Wood;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WoodDao implements ParentDao<Wood> {

    @Override
    public List<Wood> getAll() {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            List<Wood> wood = session.createQuery("from Wood", Wood.class).list();
            tx.commit();
            return wood;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public boolean add(Wood wood) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.persist(wood);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Wood wood) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.remove(wood);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Wood wood) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.merge(wood);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}
