package data_bases.hw1.task_05;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HumanDAO {
    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    private final String SQL_PET_GET_BY_ID = "select * from %s where id = ?";
    private final String SQL_GET_ALL = "select * from %s order by id";
    private final String SQL_DELETE_BY_ID = "delete from %s where id = ?";
    private final String SQL_UPDATE_BY_ID = "update %s set %s where id = ?";

    public HumanDAO(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    Cat getCatById(int id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(SQL_PET_GET_BY_ID, "cats"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Cat cat = null;
            while (resultSet.next()) {
                cat = getCatFromResultSet(resultSet);
            }
            return cat;
        } catch (SQLException e) {
            throw e;
        }
    }

    List<Cat> getAllCats() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(SQL_GET_ALL, "cats"));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Cat> cats = new ArrayList<>();
            while (resultSet.next()) {
                cats.add(getCatFromResultSet(resultSet));
            }
            return cats;
        } catch (SQLException e) {
            throw e;
        }
    }

    Dog getDogById(int id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(SQL_PET_GET_BY_ID, "dogs"));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Dog dog = null;
            while (resultSet.next()) {
                dog = getDogFromResultSet(resultSet);
            }
            return dog;
        } catch (SQLException e) {
            throw e;
        }
    }

    List<Dog> getAllDogs() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(SQL_GET_ALL, "dogs"));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Dog> dogs = new ArrayList<>();
            while (resultSet.next()) {
                dogs.add(getDogFromResultSet(resultSet));
            }
            return dogs;
        } catch (SQLException e) {
            throw e;
        }
    }

    List<Human> getAllHumans() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(SQL_GET_ALL, "humans_v3"));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Human> humans = new ArrayList<>();
            while (resultSet.next()) {
                humans.add(getHumanFromResultSet(resultSet));
            }
            return humans;
        } catch (SQLException e) {
            throw e;
        }
    }

    void removeHuman(Human human) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Dog dog = human.getDog();
            Cat cat = human.getCat();
            if(dog != null) {
                PreparedStatement removeDog = connection.prepareStatement(String.format(SQL_DELETE_BY_ID, "dogs"));
                removeDog.setInt(1, dog.getId());
                removeDog.executeUpdate();
            }
            if(cat != null) {
                PreparedStatement removeCat = connection.prepareStatement(String.format(SQL_DELETE_BY_ID, "cats"));
                removeCat.setInt(1, cat.getId());
                removeCat.executeUpdate();
            }
            PreparedStatement removeHuman = connection.prepareStatement(String.format(SQL_DELETE_BY_ID, "humans_v3"));
            removeHuman.setInt(1, human.getId());
            removeHuman.executeUpdate();
        }
    }

    void removeCatFromHuman(Human human) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(SQL_UPDATE_BY_ID, "humans_v3", "cat_id = null"));
            preparedStatement.setInt(1, human.getId());
            preparedStatement.executeUpdate();
        }
    }

    void removeDogFromHuman(Human human) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(SQL_UPDATE_BY_ID, "humans_v3", "dog_id = null"));
            preparedStatement.setInt(1, human.getId());
            preparedStatement.executeUpdate();
        }
    }

    private Cat getCatFromResultSet(ResultSet resultSet) throws SQLException {
        Cat cat = new Cat();
        cat.setId(resultSet.getInt(1));
        cat.setName(resultSet.getString(2));
        cat.setAge(resultSet.getInt(3));
        cat.setBreed(resultSet.getString(4));
        cat.setColor(resultSet.getString(5));
        return cat;
    }

    private Dog getDogFromResultSet(ResultSet resultSet) throws SQLException {
        Dog dog = new Dog();
        dog.setId(resultSet.getInt(1));
        dog.setName(resultSet.getString(2));
        dog.setAge(resultSet.getInt(3));
        dog.setBreed(resultSet.getString(4));
        dog.setColor(resultSet.getString(5));
        return dog;
    }

    //        "insert into humans_v3(name, surname, patronymic, age, cat_id, dog_id) values(?,?,?,?,?,?)";
    private Human getHumanFromResultSet(ResultSet resultSet) throws SQLException {
        Human human = new Human();
        human.setId(resultSet.getInt(1));
        human.setName(resultSet.getString(2));
        human.setSurname(resultSet.getString(3));
        human.setPatronymic(resultSet.getString(4));
        human.setAge(resultSet.getInt(5));
        try {
            human.setCat(getCatById(resultSet.getInt(6)));
        } catch (SQLException e) {
            human.setCat(null);
        }
        try {
            human.setDog(getDogById(resultSet.getInt(7)));
        } catch (SQLException e) {
            human.setDog(null);
        }
        return human;
    }

}
