package data_bases.hw2.task_02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HistoryDAO {
    private static final SessionFactory sessionFactory = UtilSessionFactory.getSessionFactory();

    public boolean save(History history){
        try (Session session = sessionFactory.openSession()) {
            System.out.println("save history");
            Transaction transaction = session.beginTransaction();
            session.persist(history);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<History> getHistory(){
            return sessionFactory.openSession()
                    .createQuery("from History", History.class)
                    .list();
    }
}
