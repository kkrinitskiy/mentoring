package data_bases.hw1.task_03;

import java.sql.*;

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


    /**
     * Выводим в консоль всех людей из таблицы
     */
    public void printAllHuman(){
        try(Connection connection = DriverManager.getConnection(url, username, password)){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_HUMANS_SELECT_ALL);

            while (resultSet.next()) {
                printHumanInfo(resultSet);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    /**
     *  Добавляем людей в таблицу
     *  
     * @param name
     * @param surname
     * @param patronymic
     * @param age
     * @param passport
     * @param positionName
     */
    public void addHuman(String name, String surname, String patronymic, int age, int passport, String positionName){
        try(Connection connection = DriverManager.getConnection(url, username, password)){

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_HUMANS_INSERT);
            try {

                // ищем пользователя, если не находим - бросаем исключение
                int positionId = findPositionIdByName(connection, positionName);

                executePreparedStatement(preparedStatement, name, surname, patronymic, age, passport, positionId);
            } catch (SQLException e) {

                // если должность не найдена в таблице, то создаем ее
                // Statement.RETURN_GENERATED_KEYS позволяет сразу получить нам id созданной записи
                PreparedStatement addPosition = connection.prepareStatement(SQL_POSITIONS_INSERT, Statement.RETURN_GENERATED_KEYS);
                addPosition.setString(1, positionName);
                addPosition.executeUpdate();

                // полученный id передаем вместе с остальными данными в sql запрос и создаем запись в таблице
                ResultSet generatedKeys = addPosition.getGeneratedKeys();
                if (generatedKeys.next()) {
                    executePreparedStatement(preparedStatement, name, surname, patronymic, age, passport, generatedKeys.getInt(1));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     *  Удаляем человека из таблицы
     *  
     * @param name
     * @param surname
     * @param patronymic
     * @param age
     * @param passportNumber
     * @param positionName
     */
    public void deleteHuman(String name, String surname, String patronymic, int age, int passportNumber, String positionName){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            int positionId = findPositionIdByName(connection, positionName);
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL_HUMANS_DELETE);
            executePreparedStatement(preparedStatement, name, surname, patronymic, age, passportNumber, positionId);
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

    private void executePreparedStatement(PreparedStatement preparedStatement, String name, String surname, String patronymic, int age, int passport, int positionId) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, patronymic);
        preparedStatement.setInt(4, age);
        preparedStatement.setInt(5, passport);
        preparedStatement.setInt(6, positionId);
        preparedStatement.executeUpdate();
    }

    private void printHumanInfo(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        String patronymic = resultSet.getString(4);
        int age = resultSet.getInt(5);
        int passport = resultSet.getInt(6);
        String position = resultSet.getString(7);

        System.out.println(id + " " + name + " " + surname + " " + patronymic + " " + age + " " + passport + " " + position);
    }
    
    

}
