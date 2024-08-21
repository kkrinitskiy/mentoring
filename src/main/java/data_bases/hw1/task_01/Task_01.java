package data_bases.hw1.task_01;

import java.sql.*;

import static data_bases.DockerPostgresContainer.*;


class Task_01 {

    private static String addHuman = "insert into humans(\"Имя\",\"Фамилия\",\"Отчество\",\"Возраст\") values(?,?,?,?)";
    private static Human[] humans = new Human[]{
            new Human("Николай", "Арсеньтьевич", "Больжедор", 55),
            new Human("Зигфрид", "Эльдарович", "Куролесов", 42),
            new Human("Аделаида", "Прокофьевна", "Шнырь", 63),
            new Human("Ермолай", "Вольдемарович", "Пупкин", 38),
            new Human("Глафира", "Феоктистовна", "Цапля", 71),
            new Human("Спиридон", "Амвросиевич", "Жмых", 50),
            new Human("Капитолина", "Эрнестовна", "Пышка", 29),
            new Human("Фаддей", "Никифорович", "Пузырь", 67),
            new Human("Ефросинья", "Велимировна", "Коржик", 45),
            new Human("Пантелеймон", "Феофанович", "Бубликов", 58)
    };

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)){

            Statement statement = connection.createStatement();

            statement.executeUpdate("drop table if exists humans");
            statement.executeUpdate("create table humans(ID bigserial primary key, \"Имя\" varchar(255), \"Фамилия\" varchar(255), \"Отчество\" varchar(255), \"Возраст\" int)");

            PreparedStatement preparedStatement = connection.prepareStatement(addHuman);

            for (Human human : humans) {
                preparedStatement.setString(1, human.name());
                preparedStatement.setString(2, human.surname());
                preparedStatement.setString(3, human.patronymic());
                preparedStatement.setInt(4, human.age());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

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
            e.printStackTrace();
        }

    }
}

record Human(String name, String surname, String patronymic, int age){};
