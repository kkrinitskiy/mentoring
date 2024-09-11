package data_bases.hw2.task_03.daos;

import data_bases.hw2.task_03.entities.TypesOfBodies;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TypesOfBodiesDao implements ParentDao<TypesOfBodies> {

    @Override
    public List<TypesOfBodies> getAll() {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            List<TypesOfBodies> types = session.createQuery("from TypesOfBodies", TypesOfBodies.class).list();
            tx.commit();
            return types;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public boolean add(TypesOfBodies type) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.persist(type);
            tx.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(TypesOfBodies type) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.remove(type);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean update(TypesOfBodies type) {
        Transaction tx = null;
        try(Session session = getSession()) {
            tx = session.beginTransaction();
            session.merge(type);
            tx.commit();
            return true;
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}
