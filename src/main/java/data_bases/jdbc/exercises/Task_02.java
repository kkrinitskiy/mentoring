package data_bases.jdbc.exercises;

import java.sql.*;
import java.text.MessageFormat;

public class Task_02 {

    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/db_name",
                "psql_user",
                "111111"
        )){

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                String output = MessageFormat.format(
                        "ID: {0}, Name: {1}, Email: {2}",
                        resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("email"));
                System.out.println(output);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
