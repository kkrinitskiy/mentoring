package stuff.hibernate.userAndCommentDao;

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
        User user = userDao.getYoungestUser();
        System.out.println(user);
        System.out.println(commentDao.getCommentsByUserId(users.get(5)));
        System.out.println(userDao.getFirstCountUsersFromStart(1));
        userDao.getFirstCountUsersFromStart(5).forEach(System.out::println);


    }

}
