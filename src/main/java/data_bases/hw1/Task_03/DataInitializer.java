package data_bases.hw1.Task_03;

import javax.swing.text.Position;
import java.sql.*;
import java.util.Random;

import static data_bases.DockerPostgresContainer.*;
import static data_bases.DockerPostgresContainer.PASSWORD;

public class DataInitializer {

    private static String addHuman = "insert into humans_v2(\"Имя\",\"Фамилия\",\"Отчество\",\"Возраст\", \"Номер паспорта\", \"ID Должности\") values(?,?,?,?,?,?)";
    private static String addPosition = "insert into positions(\"Должность\") values(?)";

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

    private static String[] positions = new String[]{"Механик", "Медбрат", "Java-разработчик", "Метролог", "Чиновник"};
    
    public static void run(){
        try(Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)){

            Statement statement = connection.createStatement();

            statement.executeUpdate("drop table if exists positions cascade");
            statement.executeUpdate("create table positions(ID bigserial primary key, \"Должность\" varchar(255))");



            statement.executeUpdate("drop table if exists humans_v2");
            statement.executeUpdate("create table humans_v2(ID bigserial primary key, \"Имя\" varchar(255), \"Фамилия\" varchar(255), \"Отчество\" varchar(255), \"Возраст\" int, \"Номер паспорта\" int, \"ID Должности\" int, foreign key (\"ID Должности\") references positions(ID))");

            PreparedStatement statementToAddPositions = connection.prepareStatement(addPosition);

            for (String position : positions) {
                statementToAddPositions.setString(1, position);
                statementToAddPositions.addBatch();
            }
            statementToAddPositions.executeBatch();


            PreparedStatement statementToAddHumans = connection.prepareStatement(addHuman);

            for (Human human : humans) {
                statementToAddHumans.setString(1, human.name());
                statementToAddHumans.setString(2, human.surname());
                statementToAddHumans.setString(3, human.patronymic());
                statementToAddHumans.setInt(4, human.age());
                statementToAddHumans.setInt(5, new Random().nextInt(100_000, 999_999));
                statementToAddHumans.setInt(6, new Random().nextInt(1, 6));
                statementToAddHumans.addBatch();
            }

            statementToAddHumans.executeBatch();

            ResultSet getHumans = statement.executeQuery("select humans_v2.ID, \"Имя\",\"Фамилия\",\"Отчество\",\"Возраст\", \"Номер паспорта\", \"Должность\" from humans_v2 join positions on positions.ID = humans_v2.\"ID Должности\"");

            while (getHumans.next()) {
                int id = getHumans.getInt(1);
                String name = getHumans.getString(2);
                String surname = getHumans.getString(3);
                String patronymic = getHumans.getString(4);
                int age = getHumans.getInt(5);
                int passport = getHumans.getInt(6);
                String position = getHumans.getString(7);

                System.out.println(id + " " + name + " " + surname + " " + patronymic + " " + age + " " + passport + " " + position);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    record Human(String name, String surname, String patronymic, int age){}
}
