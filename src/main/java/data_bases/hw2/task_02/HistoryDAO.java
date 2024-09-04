package data_bases.hw2.task_02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HistoryDAO {
    private static final SessionFactory sessionFactory = UtilSessionFactory.getSessionFactory();

    public boolean save(History history){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(history);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public List<History> getHistory(){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<History> historyList = session.createQuery("from History", History.class).list();
            transaction.commit();
            return historyList;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
