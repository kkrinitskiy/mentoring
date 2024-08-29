package data_bases.hw1.task_04;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class HumanDatabase {

    private final String url;
    private final String username;
    private final String password;

    private final static String SQL_HUMANS_INSERT = "insert into humans_v2(name, surname, patronymic, age, passport_number, position_id) values(?,?,?,?,?,?)";
    private final static String SQL_HUMANS_SELECT_ALL = "select human_id, name, surname, patronymic, age, passport_number, position_name from humans_v2 h join positions p on h.position_id = p.position_id order by human_id";
    private final static String SQL_HUMANS_DELETE = "delete from humans_v2 where name=? and surname=? and patronymic=? and age=? and passport_number=? and position_id=?";

    private final static String SQL_POSITIONS_FIND_BY_NAME = "select position_id from positions where position_name = ?";
    private final static String SQL_POSITIONS_INSERT = "insert into positions(position_name) values(?)";
    
    public HumanDatabase(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public List<Human> getAllHumans(){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_HUMANS_SELECT_ALL);
            List<Human> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(mapResultSetToHuman(resultSet));
            }

            return result;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public void addHuman(Human human){
        try(Connection connection = DriverManager.getConnection(url, username, password)){

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_HUMANS_INSERT);
            try {
                executePreparedStatement(preparedStatement, human);
            } catch (SQLException e) {
                // если должность не найдена в таблице, то создаем ее
                PreparedStatement addPosition = connection.prepareStatement(SQL_POSITIONS_INSERT);
                addPosition.setString(1, human.positionName());
                addPosition.executeUpdate();
                executePreparedStatement(preparedStatement, human);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public void deleteHuman(Human human){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL_HUMANS_DELETE);
            executePreparedStatement(preparedStatement, human);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * Этот метод ищет в бд должность по имени и возвращает ее id
     * Если не находит, то бросает исключение (в качестве обработки можно создать запись)
     * 
     * @param connection
     * @param position
     * @return
     * @throws SQLException
     */
    private int findPositionIdByName(Connection connection, String position) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_POSITIONS_FIND_BY_NAME);
        preparedStatement.setString(1, position);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        throw new SQLException("Должность " + position + " не найдена");
    }

    private void executePreparedStatement(PreparedStatement preparedStatement, Human human) throws SQLException {
        int positionId = findPositionIdByName(preparedStatement.getConnection(), human.positionName());
        preparedStatement.setString(1, human.name());
        preparedStatement.setString(2, human.surname());
        preparedStatement.setString(3, human.patronymic());
        preparedStatement.setInt(4, human.age());
        preparedStatement.setInt(5, human.passportNumber());
        preparedStatement.setInt(6, positionId);
        preparedStatement.executeUpdate();
    }

    private Human mapResultSetToHuman(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        String patronymic = resultSet.getString(4);
        int age = resultSet.getInt(5);
        int passportNumber = resultSet.getInt(6);
        String positionName = resultSet.getString(7);

        return new Human(id, name, surname, patronymic, age, passportNumber, positionName);
    }

    public void injectSql(String sql) throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
    }
}
