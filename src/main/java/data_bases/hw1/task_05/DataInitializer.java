package data_bases.hw1.task_05;

import data_bases.DockerPostgresContainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static data_bases.DockerPostgresContainer.*;

class DataInitializer {

    private static Random random = new Random();

    private static List<Cat> cats = new ArrayList<>();
    private static List<Dog> dogs = new ArrayList<>();
    private static List<Human> humans = new ArrayList<>();

    private static String SQL_CAT_INSERT = "insert into cats(name, age, breed, color) values(?,?,?,?)";

    private static String SQL_DOG_INSERT = "insert into dogs(name, age, breed, color) values(?,?,?,?)";

    private static String SQL_HUMAN_INSERT = "insert into humans_v3(name, surname, patronymic, age, cat_id, dog_id) values(?,?,?,?,?,?)";


    public static void run() {
        try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)) {

            Statement statement = connection.createStatement();

            statement.executeUpdate("drop table if exists cats cascade");
            statement.executeUpdate("create table cats(id bigserial primary key , name varchar(255), age int, breed varchar(255), color varchar(255))");

            statement.executeUpdate("drop table if exists dogs cascade");
            statement.executeUpdate("create table dogs(id bigserial primary key, name varchar(255), age int, breed varchar(255), color varchar(255))");

            statement.executeUpdate("drop table if exists humans_v3");
//            statement.executeUpdate("create table humans_v3(id bigserial primary key, name varchar(255), surname varchar(255), patronymic varchar(255), age int, cat_id int, dog_id int, foreign key (cat_id) references cats(id), foreign key (dog_id) references dogs(id))");
            statement.executeUpdate("create table humans_v3(id bigserial primary key, name varchar(255), surname varchar(255), patronymic varchar(255), age int, cat_id int, dog_id int, foreign key (cat_id) references cats(id) on delete set null , foreign key (dog_id) references dogs(id) on delete set null )");

            fillDataCatsTable();
            fillDataDogsTable();
            fillDataHumansTable();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void fillDataCatsTable() throws SQLException {
        cats.add(Cat.builder().name("Мурзик").age(3).breed("Персидская").color("Белый").build());
        cats.add(Cat.builder().name("Барсик").age(5).breed("Сиамская").color("Серый").build());
        cats.add(Cat.builder().name("Том").age(2).breed("Британская").color("Черный").build());
        cats.add(Cat.builder().name("Симба").age(4).breed("Мейн-кун").color("Оранжевый").build());
        cats.add(Cat.builder().name("Луна").age(1).breed("Шотландская").color("Серый").build());
        cats.add(Cat.builder().name("Котя").age(7).breed("Русская голубая").color("Синий").build());
        cats.add(Cat.builder().name("Нюся").age(3).breed("Абиссинская").color("Кремовый").build());
        cats.add(Cat.builder().name("Милашка").age(6).breed("Бенгальская").color("Коричневый").build());
        cats.add(Cat.builder().name("Рысь").age(2).breed("Сфинкс").color("Розовый").build());
        cats.add(Cat.builder().name("Снежок").age(8).breed("Балийская").color("Белый").build());

        try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CAT_INSERT);
            for (Cat cat : cats) {
                preparedStatement.setString(1, cat.getName());
                preparedStatement.setInt(2, cat.getAge());
                preparedStatement.setString(3, cat.getBreed());
                preparedStatement.setString(4, cat.getColor());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void fillDataDogsTable() throws SQLException {
        dogs.add(Dog.builder().name("Шарик").age(4).breed("Лабрадор").color("Черный").build());
        dogs.add(Dog.builder().name("Дружок").age(3).breed("Бульдог").color("Бежевый").build());
        dogs.add(Dog.builder().name("Рекс").age(5).breed("Немецкая овчарка").color("Черный").build());
        dogs.add(Dog.builder().name("Тузик").age(2).breed("Сибирский хаски").color("Серый").build());
        dogs.add(Dog.builder().name("Малышка").age(1).breed("Йоркширский терьер").color("Кремовый").build());
        dogs.add(Dog.builder().name("Бобик").age(7).breed("Кокер-спаниель").color("Рыжий").build());
        dogs.add(Dog.builder().name("Грей").age(6).breed("Бордер-колли").color("Чёрный с белым").build());
        dogs.add(Dog.builder().name("Лаки").age(3).breed("Пудель").color("Белый").build());
        dogs.add(Dog.builder().name("Сэм").age(2).breed("Австралийская овчарка").color("Коричневый").build());
        dogs.add(Dog.builder().name("Чарли").age(4).breed("Бассет-хаунд").color("Тигровый").build());

        try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DOG_INSERT);
            for (Dog dog : dogs) {
                preparedStatement.setString(1, dog.getName());
                preparedStatement.setInt(2, dog.getAge());
                preparedStatement.setString(3, dog.getBreed());
                preparedStatement.setString(4, dog.getColor());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void fillDataHumansTable() throws SQLException {
        humans.add(Human.builder().name("Иван").surname("Иванов").patronymic("Иванович").age(30).build());
        humans.add(Human.builder().name("Мария").surname("Петрова").patronymic("Петровна").age(25).build());
        humans.add(Human.builder().name("Алексей").surname("Сидоров").patronymic("Алексеевич").age(40).build());
        humans.add(Human.builder().name("Ольга").surname("Кузнецова").patronymic("Сергеевна").age(28).build());
        humans.add(Human.builder().name("Дмитрий").surname("Смирнов").patronymic("Дмитриевич").age(35).build());
        humans.add(Human.builder().name("Анна").surname("Васильева").patronymic("Андреевна").age(22).build());
        humans.add(Human.builder().name("Сергей").surname("Попов").patronymic("Сергеевич").age(45).build());
        humans.add(Human.builder().name("Елена").surname("Морозова").patronymic("Игоревна").age(32).build());
        humans.add(Human.builder().name("Николай").surname("Новиков").patronymic("Николаевич").age(50).build());
        humans.add(Human.builder().name("Татьяна").surname("Зайцева").patronymic("Викторовна").age(29).build());

        try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_HUMAN_INSERT);
            for (Human human : humans) {
                preparedStatement.setString(1, human.getName());
                preparedStatement.setString(2, human.getSurname());
                preparedStatement.setString(3, human.getPatronymic());
                preparedStatement.setInt(4, human.getAge());
                setRandomPetsOrNull(preparedStatement);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setRandomPetsOrNull(PreparedStatement preparedStatement) throws SQLException {
        if (random.nextInt(1, 11) % 2 == 0) {
            preparedStatement.setNull(5, Types.INTEGER);
        } else {
            preparedStatement.setInt(5, random.nextInt(1, 11));
        }
        if (random.nextInt(1, 11) % 2 == 0) {
            preparedStatement.setNull(6, Types.INTEGER);
        } else {
            preparedStatement.setInt(6, random.nextInt(1, 11));
        }
    }
}
