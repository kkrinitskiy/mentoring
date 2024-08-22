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


    public static void run(){
        try(Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)) {

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


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    private static void fillDataCatsTable() throws SQLException {
        cats.add(getCat("Мурзик", 3, "Персидская", "Белый"));
        cats.add(getCat("Барсик", 5, "Сиамская", "Серый"));
        cats.add(getCat("Том", 2, "Британская", "Черный"));
        cats.add(getCat("Симба", 4, "Мейн-кун", "Оранжевый"));
        cats.add(getCat("Луна", 1, "Шотландская", "Серый"));
        cats.add(getCat("Котя", 7, "Русская голубая", "Синий"));
        cats.add(getCat("Нюся", 3, "Абиссинская", "Кремовый"));
        cats.add(getCat("Милашка", 6, "Бенгальская", "Коричневый"));
        cats.add(getCat("Рысь", 2, "Сфинкс", "Розовый"));
        cats.add(getCat("Снежок", 8, "Балийская", "Белый"));

        try(Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CAT_INSERT);
            for (Cat cat : cats) {
                preparedStatement.setString(1, cat.getName());
                preparedStatement.setInt(2, cat.getAge());
                preparedStatement.setString(3, cat.getBreed());
                preparedStatement.setString(4, cat.getColor());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static Cat getCat(String name, int age, String breed, String color){
        Cat cat = new Cat();
        cat.setName(name);
        cat.setAge(age);
        cat.setBreed(breed);
        cat.setColor(color);
        return cat;
    }

    private static void fillDataDogsTable() throws SQLException {
        dogs.add(getDog("Шарик", 4, "Лабрадор", "Черный"));
        dogs.add(getDog("Дружок", 3, "Бульдог", "Бежевый"));
        dogs.add(getDog("Рекс", 5, "Немецкая овчарка", "Черный"));
        dogs.add(getDog("Тузик", 2, "Сибирский хаски", "Серый"));
        dogs.add(getDog("Малышка", 1, "Йоркширский терьер", "Кремовый"));
        dogs.add(getDog("Бобик", 7, "Кокер-спаниель", "Рыжий"));
        dogs.add(getDog("Грей", 6, "Бордер-колли", "Чёрный с белым"));
        dogs.add(getDog("Лаки", 3, "Пудель", "Белый"));
        dogs.add(getDog("Сэм", 2, "Австралийская овчарка", "Коричневый"));
        dogs.add(getDog("Чарли", 4, "Бассет-хаунд", "Тигровый"));

        try(Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DOG_INSERT);
            for (Dog dog : dogs) {
                preparedStatement.setString(1, dog.getName());
                preparedStatement.setInt(2, dog.getAge());
                preparedStatement.setString(3, dog.getBreed());
                preparedStatement.setString(4, dog.getColor());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static Dog getDog(String name, int age, String breed, String color){
        Dog dog = new Dog();
        dog.setName(name);
        dog.setAge(age);
        dog.setBreed(breed);
        dog.setColor(color);
        return dog;
    }

    private static void fillDataHumansTable() throws SQLException {
        humans.add(getHuman("Иван", "Иванов", "Иванович", 30));
        humans.add(getHuman("Мария", "Петрова", "Петровна", 25));
        humans.add(getHuman("Алексей", "Сидоров", "Алексеевич", 40));
        humans.add(getHuman("Ольга", "Кузнецова", "Сергеевна", 28));
        humans.add(getHuman("Дмитрий", "Смирнов", "Дмитриевич", 35));
        humans.add(getHuman("Анна", "Васильева", "Андреевна", 22));
        humans.add(getHuman("Сергей", "Попов", "Сергеевич", 45));
        humans.add(getHuman("Елена", "Морозова", "Игоревна", 32));
        humans.add(getHuman("Николай", "Новиков", "Николаевич", 50));
        humans.add(getHuman("Татьяна", "Зайцева", "Викторовна", 29));

        try(Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)){
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
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void setRandomPetsOrNull(PreparedStatement preparedStatement) throws SQLException {
        if(random.nextInt(1, 11) % 2 == 0){
            preparedStatement.setNull(5, Types.INTEGER);
        }else{
            preparedStatement.setInt(5, random.nextInt(1, 11));
        }
        if(random.nextInt(1, 11) % 2 == 0){
            preparedStatement.setNull(6, Types.INTEGER);
        }else{
            preparedStatement.setInt(6, random.nextInt(1, 11));
        }
    }

    private static Human getHuman(String name, String surname, String patronymic, int age){
        Human human = new Human();
        human.setName(name);
        human.setSurname(surname);
        human.setPatronymic(patronymic);
        human.setAge(age);
        human.setDog(null);
        human.setCat(null);
        return human;
    }
}
