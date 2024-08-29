package data_bases.jdbc.exercises;

import java.sql.*;
import java.text.MessageFormat;
import java.util.Scanner;

public class Task_03 {
    private static Scanner scanner = new Scanner(System.in);
    private static String sqlAdd = "insert into users(user_name, email, created_at) values(?, ?, ?)";
    private static String sqlUpdateEmail = "update users set email = ? where id = ?";
    private static String sqlUpdateName = "update users set user_name = ? where id = ?";
    private static String sqlDelete = "delete from users where id = ?";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/db_name",
                "psql_user",
                "111111")){

            Statement statement = connection.createStatement();

            while (true){
                ResultSet resultSet = statement.executeQuery("select * from users");
                while (resultSet.next()){
                    System.out.println(
                            MessageFormat.format(
                                    "id: {0} | name: {1} | email: {2}",
                                    resultSet.getInt("id"),
                                    resultSet.getString("user_name"),
                                    resultSet.getString("email"))
                    );
                }
                System.out.println("1 - добавить запись\n2 - изменить запись \n3 - удалить запись\n4 - выход");
                String input = scanner.nextLine();
                switch (input){
                    case "1" -> {
                        add(connection.prepareStatement(sqlAdd));
                    }
                    case "2" -> {
                        update(connection);
                    }
                    case "3" -> {
                        delete(connection.prepareStatement(sqlDelete));
                    }
                    case "4" -> System.exit(0);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void update(Connection connection) throws SQLException {
        System.out.println("id изменяемой записи:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("1 - изменить имя\n2 - изменить email");
        String input = scanner.nextLine();
        switch (input){
            case "1" -> {
                editName(id, connection.prepareStatement(sqlUpdateName));
            }
            case "2" -> {
                editEmail(id, connection.prepareStatement(sqlUpdateEmail));
            }
        }
    }

    private static void add(PreparedStatement preparedStatement) throws SQLException {
        System.out.println("Имя:");
        String name = scanner.nextLine();
        System.out.println("Email:");
        String email = scanner.nextLine();
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
        preparedStatement.executeUpdate();
    }

    private static void editEmail(int id, PreparedStatement preparedStatement) throws SQLException {
        System.out.println("Новый email:");
        String email = scanner.nextLine();
        preparedStatement.setString(1, email);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        System.out.println(email + " изменено ");
    }

    private static void editName(int id, PreparedStatement preparedStatement) throws SQLException {
        System.out.println("Новое имя:");
        String name = scanner.nextLine();
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }

    private static void delete(PreparedStatement preparedStatement) throws SQLException {
        System.out.println("id удаляемой записи:");
        int id = scanner.nextInt();
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
