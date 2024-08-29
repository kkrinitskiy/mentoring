package data_bases.jdbc;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static data_bases.DockerPostgresContainer.*;


public class jdbc_driverManager {
    public static void main(String[] args) {
        init();
        String sqlAddUser = "insert into users(name, password, created_at) values(?, ?, ?)";
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm:ss");

        try(Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)){
            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table if exists users");
            statement.executeUpdate("create table users(id bigserial primary key, name varchar(255), password varchar(255), created_at timestamp)");

            PreparedStatement addUser = connection.prepareStatement(sqlAddUser);

            for(int i=1; i<=10; i++){
                addUser.setString(1, "user_" + i);
                addUser.setString(2, "password_" + i);
                addUser.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                addUser.addBatch();
            }

            int[] ints = addUser.executeBatch();

            resultSet = statement.executeQuery("select * from users");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                System.out.println(id + " " + name + " " + password + " " + createdAt.format(dateTimeFormat));
            }
            System.out.println("количество добавленных пользователей: " + ints.length);

            System.out.println("######################");

            int columnCount = resultSet.getMetaData().getColumnCount();
            for(int i=1; i<=columnCount; i++){
                System.out.println(i + " "
                        + resultSet.getMetaData().getColumnClassName(i) + " "
                        + resultSet.getMetaData().getColumnTypeName(i) + " "
                        + resultSet.getMetaData().getColumnType(i));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
