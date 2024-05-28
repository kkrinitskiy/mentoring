package lesson_06.task_14;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        int count = new Scanner(System.in).nextInt();
        int count = 10;
        ArrayList<Integer> sequence = IntStream.iterate(1, a -> a*2)
                .limit(count)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(sequence);

    }
}
