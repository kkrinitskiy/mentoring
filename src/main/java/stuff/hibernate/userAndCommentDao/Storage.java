package stuff.hibernate.userAndCommentDao;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Storage {
    private static String[][] userData = {
            {"Петя", "Петин", "petya@example.com", "password123", "petya2024", "1990-01-01"},
            {"Саша", "Смешной", "sasha@example.com", "funny_pass", "sasha_laughs", "1992-02-29"},
            {"Кот", "Мурлыка", "cat@example.com", "meow123", "purrfect_login", "2015-03-15"},
            {"Джон", "Дог", "dog@example.com", "woofwoof", "barkmaster", "1985-04-05"},
            {"Федя", "Волшебный", "fedya@example.com", "magic_password", "wizard_fedya", "1998-05-06"},
            {"Маша", "Шутка", "masha@example.com", "joke123", "masha_funny", "1987-06-07"},
            {"Иван", "Тролл", "ivan@example.com", "trollpass", "ivan_the_troll", "1993-07-08"},
            {"Лена", "Смешарик", "lena@example.com", "smesharik_pass", "lena_smesharik", "1991-08-09"},
            {"Оля", "Космическая", "olya@example.com", "space_password", "cosmic_oly", "1995-09-10"},
            {"Гриша", "Гигант", "grisha@example.com", "bigfoot123", "grisha_the_giant", "1994-10-11"}
    };

    private static String[] commentsText = {
            "Это самый крутой пост!",
            "Не могу не согласиться с автором!",
            "Что за бред?!",
            "Смешно до слез!",
            "Я в шоке от этой новости!",
            "Какой интересный взгляд!",
            "Пожалуйста, скажите, что это шутка!",
            "Я это не понимаю...",
            "Это просто фантастика!",
            "Давайте обсудим это в комментариях!"
    };


    static List<User> getUsers(){
        List<User> users = new ArrayList<>();

        for (String[] data : userData) {
            users.add(getUser(data[0], data[1], data[2], data[3], data[4], data[5]));
        }

        return users;
    }

    static private User getUser(String firstName, String lastName, String email, String password, String login, String birthday) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setBirthdate(Date.valueOf(birthday));
        return user;
    }

    static List<Comment> getComments(List<User> users) {
        List<Comment> comments = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setText(commentsText[random.nextInt(commentsText.length)]);
            comment.setUser(users.get(random.nextInt(users.size())));
            comment.setCreated(LocalDateTime.now().minusDays(random.nextInt(30)));
            comments.add(comment);
        }
        return comments;
    }
}
