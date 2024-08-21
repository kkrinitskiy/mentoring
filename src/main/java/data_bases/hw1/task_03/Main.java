package data_bases.hw1.task_03;

import static data_bases.DockerPostgresContainer.*;

class Main {
    public static void main(String[] args) {
        System.out.println("########### ИНИЦАЛИЗАЦИЯ ДАННЫХ ###########");
        DataInitializer.run();
        System.out.println("########### ИНИЦИАЛИЗАЦИЯ ЗАВЕРШЕНА ###########");
        HumanDatabase humanDatabase = new HumanDatabase(URL + DB_NAME, USERNAME, PASSWORD);
        humanDatabase.printAllHuman();
        humanDatabase.addHuman("Порфирий", "Шуба", "Зинаидович", 650, 111_111, "Купец");
        humanDatabase.printAllHuman();
        humanDatabase.deleteHuman("Порфирий", "Шуба", "Зинаидович", 650, 111_111, "Купец");
        humanDatabase.printAllHuman();
    }
}
