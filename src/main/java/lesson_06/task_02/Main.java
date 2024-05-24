package lesson_06.task_02;


import java.util.Date;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isEvenAndTwoDigit = a -> (a % 2) == 0 && a / 100 < 1 && (double)a / 100 >= 0.1;

        int[] a = new int[]{25, 99, -8, 102, 26, 804, 44};
        int[] b = new int[]{-7, 666, 27, 39, 11, 0};
        int[] c = new int[]{10, 20, 30, 40};

        System.out.println(getSumAllEvens(isEvenAndTwoDigit, a));

    }

    public static int getSumAllEvens(Predicate<Integer> predicate, int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if(predicate.test(array[i])){
                sum+=array[i];
            }
        }
        return sum;
    }
}
