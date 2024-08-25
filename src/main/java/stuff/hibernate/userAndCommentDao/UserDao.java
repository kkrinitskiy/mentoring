package stuff.hibernate.userAndCommentDao;

import jakarta.persistence.RollbackException;
import jakarta.persistence.TypedQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

class UserDao {
    private SessionFactory sessionFactory;

    void init(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    boolean addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return true;
        }catch (RollbackException e){
            return false;
        }
    }

    boolean updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return true;
        }catch (RollbackException e){
            return false;
        }
    }

    boolean deleteUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
            return true;
        }catch (RollbackException e){
            return false;
        }
    }

    List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    User getUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createNamedQuery("User.FindById", User.class)
                    .setParameter("id", id);
            return query.getSingleResult();
        }
    }

    List<User> getUsersByFirstName(String firstName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createNamedQuery("User.FindByFirstName", User.class)
                    .setParameter("firstName", firstName)
                    .list();
        }
    }

    List<User> getUsersByLastName(String lastName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createNamedQuery("User.FindByLastName", User.class)
                    .setParameter("lastName", lastName)
                    .list();
        }
    }

    User getYoungestUser(){
        try (Session session = sessionFactory.openSession()) {
            Query<User> usersList = session.createQuery("from User order by birthdate", User.class);
            ScrollableResults<User> scroll = usersList.scroll();
            scroll.last();
            return scroll.get();
        }
    }

    List<User> getFirstCountUsersFromStart(int count) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            query.setFirstResult(0);
            query.setMaxResults(count);
            return query.list();
        }
    }

}
