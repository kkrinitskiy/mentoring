package data_bases.hw1.task_03;

import java.sql.*;
import java.util.Random;

import static data_bases.DockerPostgresContainer.*;
import static data_bases.DockerPostgresContainer.PASSWORD;

class DataInitializer {

    private static String addHuman = "insert into humans_v2(name, surname, patronymic, age, passport_number, position_id) values(?,?,?,?,?,?)";
    private static String addPosition = "insert into positions(position_name) values(?)";

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

    private static String[] positions = new String[]{"Механик", "Медбрат", "Java-разработчик", "Метролог", "Чиновник", "Безработный"};
    
    public static void run(){
        try(Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD)){

            Statement statement = connection.createStatement();

            statement.executeUpdate("drop table if exists positions cascade");
            statement.executeUpdate("create table positions(position_id bigserial primary key, position_name varchar(255))");



            statement.executeUpdate("drop table if exists humans_v2");
            statement.executeUpdate("create table humans_v2(human_id bigserial primary key, name varchar(255), surname varchar(255), patronymic varchar(255), age int, passport_number int, position_id int, foreign key (position_id) references positions(position_id))");

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
                statementToAddHumans.setInt(6, new Random().nextInt(1, positions.length + 1));
                statementToAddHumans.addBatch();
            }

            statementToAddHumans.executeBatch();

            ResultSet getHumans = statement.executeQuery("select human_id, name, surname, patronymic, age, passport_number, position_name from humans_v2 h join positions p on h.position_id = p.position_id order by human_id");

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
