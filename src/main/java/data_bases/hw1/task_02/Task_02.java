package data_bases.hw1.task_02;

import java.sql.*;

import static data_bases.DockerPostgresContainer.*;

public class Task_02 {
    public static void main(String[] args) {
        HumansDataBase humansDataBase = new HumansDataBase(URL + DB_NAME, USERNAME, PASSWORD);
        humansDataBase.printAllHuman();
        humansDataBase.addHuman("Олег", "Смеян", "Петрович", 11);
        humansDataBase.printAllHuman();
        humansDataBase.deleteHuman("Олег", "Смеян", "Петрович", 11);
        humansDataBase.printAllHuman();

    }

    static class HumansDataBase{

        private final String url;
        private final String username;
        private final String password;

        public HumansDataBase(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public void printAllHuman(){
            try(Connection connection = DriverManager.getConnection(url, username, password)){

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from humans");

                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    String patronymic = resultSet.getString(4);
                    int age = resultSet.getInt(5);

                    System.out.println(id + " " + name + " " + surname + " " + patronymic + " " + age);
                }

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        public void addHuman(String name, String surname, String patronymic, int age){
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
}




