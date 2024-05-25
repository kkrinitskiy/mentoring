package lesson_06.task_07;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<ArrayList<Integer>, Integer> sum = ints -> {
            int result = 0;
            for (Integer anInt : ints) {
                result += anInt;
            }
            return result;
        };
    }
}
