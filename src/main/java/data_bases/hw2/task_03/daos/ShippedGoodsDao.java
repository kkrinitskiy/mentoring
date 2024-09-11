package data_bases.hw2.task_03.daos;

import data_bases.hw2.task_03.entities.ShippedGoods;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ShippedGoodsDao implements ParentDao<ShippedGoods> {

    @Override
    public List<ShippedGoods> getAll() {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            List<ShippedGoods> goods = session.createQuery("from ShippedGoods", ShippedGoods.class).list();
            tx.commit();
            return goods;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public boolean add(ShippedGoods goods) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.persist(goods);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(ShippedGoods goods) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.remove(goods);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public boolean update(ShippedGoods goods) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.merge(goods);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}
