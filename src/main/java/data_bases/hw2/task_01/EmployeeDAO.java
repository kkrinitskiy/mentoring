package data_bases.hw2.task_01;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDAO {

    private static final SessionFactory sessionFactory = UtilSessionFactory.getSessionFactory();

    public static boolean saveAll(List<Employee> employees) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            for (Employee employee : employees) {
                session.persist(employee);
            }
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean save(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(employee);
            transaction.commit();
            return true;
        }catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Employee> getAllEmployees() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from data_bases.hw2.task_01.Employee", Employee.class)
                    .list();
        }
    }

    public static List<Employee> getEmployeesSortedBySalaryThenByExpThenByLastName() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from data_bases.hw2.task_01.Employee order by salary, experience, lastName", Employee.class).list();
        }
    }

    private static class UtilSessionFactory {
        private static SessionFactory sessionFactory;

        private UtilSessionFactory() {
        }

        private static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                try {
                    sessionFactory = new Configuration()
                            .addAnnotatedClass(Employee.class)
                            .buildSessionFactory();
                } catch (HibernateException e) {
                    throw new RuntimeException(e);
                }
            }
            return sessionFactory;
        }
    }
}
