package lesson_03;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Scanner;

public class hw3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("1 ЗАДАЧА");
        int[] ints = {10, 20, 30, 40, 50};

//        ints = firstTask(scanner, ints);

        System.out.println(Arrays.toString(ints) + "\n");



        System.out.println("2 ЗАДАЧА");
        String input =
                "{" +
                        "'users' : " +
                                "{ " +
                                "'Фамилия' : 'Семёнкин', " +
                                "'Предмет' : 'Информатика', " +
                                "'Оценки на уроке' : [5, 4, 5, 5, 3, 4, 5, 5, 5, 5], " +
                                "'Оценки за контрольные' : [5, 5, 4, 5, 5, 5, 4, 5], " +
                                "'Преподаватель' : 'Иванова' " +
                                "}, " +
                                "{ " +
                                "'Фамилия' : 'Ивашин', " +
                                "'Предмет' : 'Математика', " +
                                "'Оценки на уроке' : [3, 2, 4, 4, 3, 4, 3, 5, 4, 4], " +
                                "'Оценки за контрольные' : [3, 3, 4, 5, 4, 4, 3, 5], " +
                                "'Преподаватель' : 'Иванова' " +
                                "} " +
                "}";

        System.out.println(secondTask(input) + "\n");



        System.out.println("3 ЗАДАЧА");

        int[][] ints1 =
                {
                        {10,20,30,40,50},
                        {114,10},
                        {-8,-6,0,0,7,-29}
                };

        System.out.println(Arrays.toString(getAveragesInMultidimensionalArray(ints1)));

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








    private static String secondTask(String input){
        StringBuilder result = new StringBuilder();

        String[] users = input.split("}, \\{ ");

        for (String user : users) {
           result.append(getData(user)).append("\n");
        }


        return result.toString().strip();
    }


    private static String getDiscipline(String input) {
        String str = "'Предмет' : '";

        int start = input.lastIndexOf(str) + str.length();

        int end = input.indexOf("'", start);

        return input.substring(start, end);
    }

    private static String getSurname(String input) {
        String str = "'Фамилия' : '";

        int start = input.lastIndexOf(str) + str.length();

        int end = input.indexOf("'", start);

        return input.substring(start, end);
    }

    private static double getAverageOnClassWork(String input) {
        String str = "'Оценки на уроке' : [";

        int start = input.lastIndexOf(str) + str.length();

        int end = input.indexOf("]", start);

        calculateAverage(input.substring(start, end));

        return calculateAverage(input.substring(start, end));
    }

    private static double getAverageOnTest(String input) {
        String str = "'Оценки за контрольные' : [";

        int start = input.lastIndexOf(str) + str.length();

        int end = input.indexOf("]", start);

        return calculateAverage(input.substring(start, end));
    }

    private static double calculateAverage(String grades){
        double sum = 0;
        int count = 0;
        String gradesInLine = grades.replaceAll("\\D", "");
        char[] chars = gradesInLine.toCharArray();

        for (char aChar : chars) {
            sum += Character.getNumericValue(aChar);
            count++;
        }

        return sum/count;
    }

    private static String getData(String user){

        return getDiscipline(user) + " " +
                getSurname(user) + " " +
                getAverageOnClassWork(user) + " " +
                getAverageOnTest(user);
    }








    private static int[] getAveragesInMultidimensionalArray(int[][] ints){
        int[] result = new int[ints.length];

        for (int i = 0; i < ints.length; i++) {
            int sum = 0;
            int count = 0;
            for (int j = 0; j < ints[i].length; j++) {
                sum += ints[i][j];
                count++;
            }
            result[i] = sum/count;
        }

        return result;
    }

}
