package stuff.hibernate.userAndCommentDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CommentDao {
    private SessionFactory sessionFactory;

    void init(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    boolean addComment(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(comment);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    boolean updateComment(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(comment);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    boolean deleteComment(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(comment);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    List<Comment> getAllComments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Comment", Comment.class).list();
        }
    }

    List<Comment> getCommentsByUserId(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("from Comment where user.id = :userId", Comment.class)
                    .setParameter("userId", user.getId())
                    .list();
        }
    }
}
