package data_bases.jdbc;

import java.sql.*;

import static data_bases.jdbc.DockerPostgresContainer.*;


public class Main {
    public static void main(String[] args) {
        init();

        try(Connection connection = DriverManager.getConnection(url+ db_name, user, password)){
            ResultSet resultSet = null;
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table if exists users");
            statement.executeUpdate("create table users(id bigserial primary key, name varchar(255), password varchar(255))");

            for(int i=1; i<=10; i++){

                statement.executeUpdate("insert into users(name, password) values('user_" + i + "','password_" + i + "')");
            }

            resultSet = statement.executeQuery("select * from users");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                System.out.println(id + " " + name + " " + password);
            }

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
