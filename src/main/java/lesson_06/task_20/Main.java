package lesson_06.task_20;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        Supplier<Human> getHuman = () -> {
            System.out.println("Введите имя");
            String name = SCANNER.nextLine();
            System.out.println("Введите фамилию");
            String surname = SCANNER.nextLine();

            return new Human(name, surname, new Random().nextInt(0,91));
        };

        System.out.println("Суммарный возраст: " + Stream.generate(getHuman).limit(5).map(Human::getAge).mapToInt(Integer::intValue).sum());



    }
}
