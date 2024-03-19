package lesson_02;

import java.util.Arrays;
import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {
//                          МАССИВЫ
//                         1 ЗАДАЧА
        int[] fir_01 = {1, 2, 3, 4, 5};             // true
        int[] sec_01 = {3, 4, 5};

        int[] fir_02 = {10, 20, 30, 40, 55, 67};    // false
        int[] sec_02 = {20, 30, 77};

        int[] fir_03 = {1, 3, 2, 4, 5};             // false
        int[] sec_03 = {3, 4, 5};

        int[] fir_04 = {1, 3, 2, 4, 5, 3, 4, 5};    // true
        int[] sec_04 = {3, 4, 5};

        System.out.println(doesContainInTheSameSequence(fir_04, sec_04));

//                          2 ЗАДАЧА TODO: сделать чтобы возвращал массив, а не костыльно печатал из метода

        int[] arms_01 = {15, 123, 0, -8, 16, 153, 142, 12};
        int[] arms_02 = {100, 110, 5, 18, 98, 421};
        int[] arms_03 = {1, 10, 555, 371, 407, 122, 153};

        hasArmstrongNumber(arms_03);

//                          3 ЗАДАЧА TODO: вероятно можно сделать в один цикл

        int[] ints_01 = {1, 2, 3, 5, 7, 9, -8, 100, 0, 1};
        System.out.println(Arrays.toString(shift(ints_01, 3)));

//                           СТРОКИ
//                          1 ЗАДАЧА

//        Привет, мне 17 лет, живу в доме номер 8
//        Около дома растут цветы
//        *** на этапе компиляции
//        10 + 5 = 15
//        !!!аыва**
//        175 - 8 ___ +
        String input = "";
        System.out.println("Введите предложение: ");
//        input = new Scanner(System.in).nextLine();
        System.out.println("Ваше предложение: \"" + input + "\"");
        System.out.println("Количество слов в предложении: " + howMuchWordsInInput(input));

//                          2 ЗАДАЧА
        String json =
                        "{" +
                        "'Предмет' : 'Информатика', " +
                        "'Оценки на уроке' : [5, 4, 5, 5, 3, 4, 5, 5, 5, 5], " +
                        "'Оценки за контрольные' : [5, 5, 4, 5, 5, 5, 4, 5], " +
                        "'Преподаватель' : 'Иванова'" +
                        "}";

        jsonParser(json);

    }

    public static boolean doesContainInTheSameSequence(int[] fir, int[] sec){
        for (int i = 0; i < fir.length; i++) {
            if(fir[i] == sec[0]){
                for (int j = 0; j < sec.length; j++) {
                    if(fir[i + j] != sec[j]){
                        break;
                    }
                    if(j == sec.length - 1 && fir[i + j] == sec[j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void hasArmstrongNumber(int[] ints){
        for (int i = 0; i < ints.length ; i++) {
            if(ints[i] > 99 && ints[i] < 1000){
                int num = ints[i];
                int sum = 0;
                while (num > 0){
                    int a = num % 10;
                    sum += (int) Math.pow(a, 3);
                    num /= 10;
                }
                if(sum == ints[i]){
                    System.out.printf("%d ", sum);
                }
            }
        }
        System.out.println();
    }

    public static int[] shift(int[] ints, int value){
        int storage;
        int[] result = new int[ints.length];

//      копируем конец первого массива в начало нового массива
        for (int i = ints.length - value, j = 0;
             (i < ints.length) && (j < value);
             i++, j ++) {
            result[j] = ints [i];
        }

//      копириуем остальное в конец нового массива
        for (int i = 0, j = value;
             (i < ints.length - value) && (j < result.length);
             i++, j++) {
            result[j] = ints[i];
        }


        return result;
    }

    public static int howMuchWordsInInput(String input){
        String[] words = input.split(" ");
        int count = 0;
        for (String word : words) {
            if (word.trim().matches("^[а-яА-Яa-zA-Z]+[,.!]?$")){
                count++;
            }
        }
        return count;
    }

    public static String jsonParser(String json){
        String[] strings = json.split("[{\\s:]? ");
        for (String string : strings) {
            System.out.println(string);
        }


        return "";
    }

}
