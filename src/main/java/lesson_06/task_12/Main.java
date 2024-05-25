package lesson_06.task_12;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 2, 30, 4, 70);
//        int input = new Scanner(System.in).nextInt();
//        int input = 10;
//        int input = 80;
        int input = 4;

        System.out.println((Integer) ints.stream().filter(a -> a > input).mapToInt(a -> a).sum());
    }
}
