package data_bases.hw2.task_03.daos;


import data_bases.hw2.task_03.UtilSessionFactory;
import org.hibernate.Session;

import java.util.List;

public interface ParentDao<T> {
    List<T> getAll();
    default boolean addList(List<T> list){
        try (Session session = getSession()) {

        }
    }
    boolean add(T t);
    boolean delete(T t);
    boolean update(T t);
    default Session getSession(){
        return UtilSessionFactory.getSessionFactory().openSession();
    }
}
