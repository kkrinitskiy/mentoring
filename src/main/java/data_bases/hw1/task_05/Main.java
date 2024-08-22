package data_bases.hw1.task_05;

import java.sql.SQLException;
import java.util.List;

import static data_bases.DockerPostgresContainer.*;

public class Main {
    public static void main(String[] args) {
        DataInitializer.run();
        HumanDAO dao = new HumanDAO(URL + DB_NAME, USERNAME, PASSWORD);
        try {

            print(dao.getAllCats());

            System.out.println("ОДНА КОШКА - " + dao.getCatById(1));

            print(dao.getAllDogs());

            System.out.println("ОДНА СОБАКА - " + dao.getDogById(1));

            List<Human> humans = dao.getAllHumans();

            print(humans);

            System.out.println("УДАЛЯЕМ ЧЕЛОВЕКА: " + humans.get(0));

            dao.removeHuman(humans.get(0));

            print(dao.getAllHumans());

            humans = dao.getAllHumans();
            Human humanToRemoveDog = humans.stream().filter(h -> h.getDog() != null).findFirst().get();
            System.out.println("ЧЕЛОВЕК С СОБАКОЙ ДЛЯ УДАЛЕНИЯ: " + humanToRemoveDog);
            dao.removeDogFromHuman(humanToRemoveDog);

            print(dao.getAllHumans());

            Human humanToRemoveCat = humans.stream().filter(h -> h.getCat() != null).findFirst().get();
            System.out.println("ЧЕЛОВЕК С КОШКОЙ ДЛЯ УДАЛЕНИЯ: " + humanToRemoveCat);
            dao.removeCatFromHuman(humanToRemoveCat);

            print(dao.getAllHumans());


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void print(List<? extends Object> os) {
        System.out.println("########################");
        for (Object o : os) {
            System.out.println(o);
        }
        System.out.println("########################");
    }
}
