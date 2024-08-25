package data_bases.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CommentDao {
    private SessionFactory sessionFactory;

    void init(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    void addComment(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(comment);
            session.getTransaction().commit();
        }
    }

    void updateComment(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(comment);
            session.getTransaction().commit();
        }
    }

    void deleteComment(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(comment);
            session.getTransaction().commit();
        }
    }

    List<Comment> getAllComments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Comment", Comment.class).list();
        }
    }
}
