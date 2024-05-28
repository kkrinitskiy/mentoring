package lesson_06.task_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<String> hasTwoE = a -> {
            a = a.toLowerCase();
            int counter = 0;
            for (int i = 0; i < a.length(); i++) {
                if(a.charAt(i) == 'e' || a.charAt(i) == 'е'){
                    counter++;
                }
            }
            if (counter >= 2){
                return true;
            }
            return false;
        };

        Predicate<String> startsWithUpperCase = a -> Character.isUpperCase(a.charAt(0));

        List<String> a = Arrays.asList("Перемена", "метель", "солнце", "Крепость еще далеко");
        List<String> b = Arrays.asList("Ручей", "липа", "зелень", "Бегемот", "веретено на столе", "Пряжа");
        List<String> c = Arrays.asList("Лего", "перелёт", "Снегопад", "На улице дождь");

        System.out.println(c.stream().filter(startsWithUpperCase.and(hasTwoE)).count());



    }
}
