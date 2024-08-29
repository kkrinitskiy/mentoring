package data_bases.hw1.task_04;

import data_bases.DockerPostgresContainer;

import java.sql.SQLException;

import static data_bases.DockerPostgresContainer.*;

public class Main {
    public static void main(String[] args) {
        DataInitializer.run();
        HumanDatabase humanDatabase = new HumanDatabase(URL + DB_NAME, USERNAME, PASSWORD);
        Human dummy = new Human(-1,"Порфирий", "Шуба", "Зинаидович", 650, 111_111, "Купец");
        for (Human human : humanDatabase.getAllHumans()) {
            System.out.println(human);
        }

        humanDatabase.addHuman(dummy);

        for (Human human : humanDatabase.getAllHumans()) {
            System.out.println(human);
        }

        humanDatabase.deleteHuman(dummy);

        for (Human human : humanDatabase.getAllHumans()) {
            System.out.println(human);
        }

        try {
            humanDatabase.injectSql("delete from humans_v2 where age = (select max(age) from humans_v2)");
        }catch (SQLException e){
            e.printStackTrace();
        }

        for (Human human : humanDatabase.getAllHumans()) {
            System.out.println(human);
        }
    }
}
