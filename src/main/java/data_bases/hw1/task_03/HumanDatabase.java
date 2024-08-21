package data_bases.hw1.task_03;

import java.sql.*;

public class HumanDatabase {

    private final String url;
    private final String username;
    private final String password;

    private final static String SQL_HUMANS_INSERT = "insert into humans_v2(name, surname, patronymic, age, passport_number, position_id) values(?,?,?,?,?,?)";
    private final static String SQL_HUMANS_SELECT_ALL = "select human_id, name, surname, patronymic, age, passport_number, position_name from humans_v2 h join positions p on h.position_id = p.position_id order by human_id";

    private final static String SQL_POSITIONS_FIND_BY_NAME = "select position_id from positions where position_name = ?";

    public HumanDatabase(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void printAllHuman(){
        try(Connection connection = DriverManager.getConnection(url, username, password)){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_HUMANS_SELECT_ALL);

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

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_HUMANS_INSERT);
            try {

                int positionId = findOrCreatePosition(connection, position);

                pushPreparedStatementAddHuman(preparedStatement, name, surname, patronymic, age, passport, positionId);
            } catch (SQLException e) {
                PreparedStatement addPosition = connection.prepareStatement("insert into positions(position_name) values(?)", Statement.RETURN_GENERATED_KEYS);
                addPosition.setString(1, position);
                addPosition.executeUpdate();

                ResultSet generatedKeys = addPosition.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pushPreparedStatementAddHuman(preparedStatement, name, surname, patronymic, age, passport, generatedKeys.getInt(1));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private int findOrCreatePosition(Connection connection, String position) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_POSITIONS_FIND_BY_NAME);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return -1;
        }catch (SQLException e){
            System.out.println("Указанная должность не найдена, добавляем в таблицу" + e.getMessage());
            return -1;
        }
    }

    private void pushPreparedStatementAddHuman(PreparedStatement preparedStatement, String name, String surname, String patronymic, int age, int passport, int positionId) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, patronymic);
        preparedStatement.setInt(4, age);
        preparedStatement.setInt(5, passport);
        preparedStatement.setInt(6, positionId);
        preparedStatement.executeUpdate();
    }



    public void deleteHuman(String name, String surname, String patronymic, int age, int passportNumber, String position_id){
        try(Connection connection = DriverManager.getConnection(url, username, password)){

            int positionId = findPositionIdByName(connection, position_id);

            if (positionId < 0) {
                throw new RuntimeException("Такой должности нет");
            }
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from humans_v2 where name=? and surname=? and patronymic=? and age=? and passport_number=? and position_id=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setInt(4, age);
            preparedStatement.setInt(5,passportNumber);
            preparedStatement.setInt(6,positionId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private int findPositionIdByName(Connection connection, String positionName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_POSITIONS_FIND_BY_NAME);
            preparedStatement.setString(1, positionName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);

            }
            return -1;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

}
