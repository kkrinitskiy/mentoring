package data_bases.jdbc.exercises;

import data_bases.DockerPostgresContainer;
import java.sql.*;
import java.time.LocalDate;

public class Task_01 {
    public static void main(String[] args) {
        DockerPostgresContainer.init();

        try(Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://localhost:5432/db_name",
                        "psql_user",
                        "111111")) {

            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table if exists users");
            statement.executeUpdate("create table if not exists users(" +
                    "id bigserial primary key, " +
                    "user_name varchar(255), " +
                    "email varchar(255), " +
                    "created_at date not null)");

            String insertUser = "insert into users(user_name, email, created_at) " +
                    "values(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertUser);

            for (int i = 1; i < 10; i++) {
                preparedStatement.setString(1, "user" + i);
                preparedStatement.setString(2, "user" + i + "@mail.com");
                preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                preparedStatement.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
