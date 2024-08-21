package data_bases.hw1.Task_03;

import java.sql.*;

public class HumanDatabase {

    private final String url;
    private final String username;
    private final String password;

    public HumanDatabase(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void printAllHuman(){
        try(Connection connection = DriverManager.getConnection(url, username, password)){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select humans_v2.ID, \"Имя\",\"Фамилия\",\"Отчество\",\"Возраст\", \"Номер паспорта\", \"Должность\" from humans_v2 join positions on positions.ID = humans_v2.\"ID Должности\"");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String patronymic = resultSet.getString(4);
                int age = resultSet.getInt(5);
                int passport = resultSet.getInt(6);
                String position = resultSet.getString(7);

                System.out.println(id + " " + name + " " + surname + " " + patronymic + " " + age + " " + passport + " " + position);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void addHuman(String name, String surname, String patronymic, int age, int passport, String position){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement preparedStatement = connection.prepareStatement("insert into humans(\"Имя\", \"Фамилия\", \"Отчество\", \"Возраст\") values(?,?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteHuman(String name, String surname, String patronymic, int age){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from humans where \"Имя\"=? and \"Фамилия\"=? and \"Отчество\"=? and \"Возраст\"=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
