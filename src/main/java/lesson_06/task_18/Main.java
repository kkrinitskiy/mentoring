package lesson_06.task_18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Integer> ints = new ArrayList<>(List.of(25, 99, -80, 102, 26, 804, 44));
        ArrayList<Integer> ints = new ArrayList<>(List.of(150, 785, 99, 800, -61, -856, 21));

        ints.sort(Comparator.comparingInt(a ->
        {
            a = Math.abs(a);
            while (a > 10){
                if(a < 100){
                    return a % 10;
                }
                a /= 10;
            }
            return 0;
        }));

        System.out.println(ints);

    }
}
