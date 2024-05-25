package lesson_06.task_15;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        UnaryOperator<Integer> sumOfNumbers = n -> {
            int sum = 0;
            while (n != 0){
                sum += n%10;
                n = n / 10;
            }
            return Math.abs(sum);
        };

        int count = 10;

        ArrayList<Integer> collectionOfNumbers = IntStream.generate(() -> new Random().nextInt())
                .limit(count)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(collectionOfNumbers);


        int maxNumSum = collectionOfNumbers.stream().reduce(
                (a, b) -> sumOfNumbers.apply(a) > sumOfNumbers.apply(b) ? a : b).get();

        for (Integer num : collectionOfNumbers) {
            System.out.println(MessageFormat.format("nums: {0} - sum: {1}", num, sumOfNumbers.apply(num)));
        }

        System.out.println("Число с наибольшей суммой цифр");
        System.out.println(MessageFormat.format("nums: {0} - sum: {1}", maxNumSum, sumOfNumbers.apply(maxNumSum)));


    }
}
