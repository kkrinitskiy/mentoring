package lesson_06.task_11;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("с", "стр", "строка", "строка побольше", "большая строка вообще ппц");

        list.stream().filter(s -> s.length() > 5).forEach(System.out::println);
    }
}
