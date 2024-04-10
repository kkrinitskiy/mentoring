package lesson_03;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Scanner;

public class hw3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


//                          1 ЗАДАЧА

        int[] ints = {10, 20, 30, 40, 50};

        ints = firstTask(scanner, ints);

        System.out.println(Arrays.toString(ints));



    }

    public static int[] firstTask(Scanner scanner, int[] ints){
        System.out.println("введите число n");
        int n = scanner.nextInt();

        int[] inputInts = new int[n];

        for (int i = 1; i <= n; i++) {
            System.out.println(MessageFormat.format("введите эл-т №{0}", i));
            inputInts[i - 1] = scanner.nextInt();
        }

        System.out.println("ввод элементов завершен");

        return growArray(ints, inputInts);
    }

    public static int[] growArray(int[] ints, int[] nums){

        int[] result = new int[ints.length + nums.length];

        for (int i = 0; i < ints.length; i++) {
            result[i] = ints[i];
        }
        for (int i = 0; i < nums.length; i++) {
            result[i + ints.length] = nums[i];
        }

        return result;

    }


}
