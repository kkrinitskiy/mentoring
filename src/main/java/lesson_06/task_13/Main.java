package lesson_06.task_13;

import java.util.Random;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                Stream.iterate(1, a -> new Random().nextInt(10, 100))
                        .filter(a -> a > 50)
                        .limit(new Random().nextInt(0, 100))
                        .count());

    }
}
