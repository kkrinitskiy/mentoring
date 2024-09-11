package data_bases.hw2.task_03.daos;


import data_bases.hw2.task_03.UtilSessionFactory;
import org.hibernate.Session;

import java.util.List;

public interface ParentDao<T> {
    List<T> getAll();
    boolean add(T t);
    boolean delete(T t);
    boolean update(T t);
    default Session getSession(){
        return UtilSessionFactory.getSessionFactory().openSession();
    }
}
