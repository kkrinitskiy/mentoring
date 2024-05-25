package lesson_06.task_17;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toyota 10","Nissan 3","BMW 7","Toyota 4","BMW 1","Reno 17","Nissan 7");

        Function<String, Integer> getNumber = str -> Integer.parseInt(str.split(" ")[1]);
        Function<String, String> getCarName = str -> str.split(" ")[0];

        int toyotaCount = list.stream()
                .filter(a -> getCarName.apply(a).equals("Toyota"))
                .map(getNumber)
                .reduce(Integer::sum)
                .get();

        System.out.println(toyotaCount);
    }
}
