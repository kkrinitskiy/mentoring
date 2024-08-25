package data_bases.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Comment.class)
                .buildSessionFactory();

        UserDao userDao = new UserDao();
        userDao.init(sessionFactory);
        CommentDao commentDao = new CommentDao();
        commentDao.init(sessionFactory);

        List<User> users = Storage.getUsers();

        for (User user : users) {
            userDao.addUser(user);
        }

        List<Comment> comments = Storage.getComments(users);

        for (Comment comment : comments) {
            commentDao.addComment(comment);
        }

        userDao.getAllUsers().forEach(System.out::println);
        commentDao.getAllComments().forEach(System.out::println);

    }

}
