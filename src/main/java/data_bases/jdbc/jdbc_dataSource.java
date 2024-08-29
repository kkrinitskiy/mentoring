package data_bases.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static data_bases.DockerPostgresContainer.*;

public class jdbc_dataSource {
    public static void main(String[] args) {
        init();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL + DB_NAME);
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(5);


        DataSource dataSource = new HikariDataSource(config);

        try(Connection conn = dataSource.getConnection()) {

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("name");
                String password = resultSet.getString("password");
                System.out.println(id + " " + username + " " + password);
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
