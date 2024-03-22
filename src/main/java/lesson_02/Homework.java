package lesson_02;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

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

        System.out.println("Первый массив содержит в себе второй: "
                + doesContainInTheSameSequence(fir_04, sec_04) + "\n");

//                          2 ЗАДАЧА

        int[] arms_01 = {15, 123, 0, -8, 16, 153, 142, 12};
        int[] arms_02 = {100, 110, 5, 18, 98, 421};
        int[] arms_03 = {1, 10, 555, 371, 407, 122, 153};

        System.out.println("Числа армстронга в переданном массиве: "
                + Arrays.toString(hasArmstrongNumber(arms_02)) + "\n");

//                          3 ЗАДАЧА TODO: вероятно можно сделать в один цикл

        int[] ints_01 = {1, 2, 3, 5, 7, 9, -8, 100, 0, 1};
        System.out.println("Резлуьтат сдвига массива: "
                + Arrays.toString(shift(ints_01, 3)) + "\n");

//                           СТРОКИ
//                          1 ЗАДАЧА
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Привет, мне 17 лет, живу в доме номер 8");
        strings.add("Около дома растут цветы");
        strings.add("*** на этапе компиляции");
        strings.add("10 + 5 = 15");
        strings.add("!!!аыва** 175 - 8 ___ +");

        for (String string : strings) {
            System.out.println("Введенное предложение: " + string);
            System.out.println("\tколичество слов в предложении: "
                    + howMuchWordsInInputNonUsingRegexMethod(string));
        }


//        String input = "";
//        System.out.println("Введите предложение: ");
////        input = new Scanner(System.in).nextLine();
//        System.out.println("\tВаше предложение: \"" + input + "\"");
//        System.out.println("\tКоличество слов в предложении: "
//                + howMuchWordsInInput(input) + "\n");

//                          2 ЗАДАЧА
	    
        String json =
                        "{" +
                        "'Предмет' : 'Информатика', " +
                        "'Оценки на уроке' : [5, 4, 5, 5, 3, 4, 5, 5, 5, 5], " +
                        "'Оценки за контрольные' : [5, 5, 4, 5, 5, 5, 4, 5], " +
                        "'Преподаватель' : 'Иванова'" +
                        "}";

         System.out.println("Результат форматирования строки: " + jsonParser(json) + "\n");
	    
//                          ARRAYLIST
//                          1 ЗАДАЧА
	ArrayList<Integer> list_01 = new ArrayList<>();
	ArrayList<Integer> list_02 = new ArrayList<>();
	    
	for(int i = 1; i < 9; i++){
		list_01.add(i);
	}
	    
	int[] ints_03 = new int[]{10, 15, 6, -8, 41, 9, 16, -5, 0};
	    
	for(int i : ints_03){
		list_02.add(i);
	}
	    
	    
	for(ArrayList<Integer> list : provideReverseAndRegularList(list_01)){
		System.out.println(list);
	}

    }

    public static boolean doesContainInTheSameSequence(int[] fir, int[] sec){
        if (sec.length > fir.length){
            return false;
        }
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


    public static int[] hasArmstrongNumber(int[] ints){
        int counter = 0;

        for (int i = 0; i < ints.length ; i++) {
           if (isArmstrong(ints[i])){
               counter++;
           }else {
               ints[i] = -1;
           }
        }

        int[] result = new int[counter];
        counter = 0;

        for (int anInt : ints) {
            if (anInt > 0) {
                result[counter] = anInt;
                counter++;
            }
        }

        if (result.length == 0){
            return new int[]{-1};
        }

        return result;
    }

    private static boolean isArmstrong(int num){
        int numbForCheck = num;
        if(num > 99 && num < 1000){
            int sum = 0;
            while (num > 0){
                int a = num % 10;
                sum += (int) Math.pow(a, 3);
                num /= 10;
            }
            if(sum == numbForCheck){
                return true;
            }
        }
        return false;
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

    public static int howMuchWordsInInputNonUsingRegexMethod(String input){

        if(input.isEmpty()){
            return 0;
        }

        int counter = 0;

        int start = 0;
        int end;
        boolean repeat = true;

        while (repeat){

            if(start + 1 < input.length()) {
                end = input.indexOf(" ", start + 1);
            } else {
                end = input.length();
            }

            if(end == -1){
                end = input.length();
                repeat = false;
            }

            String word = input.substring(start, end).strip();
            if(isWord(word)){
                counter++;
            }

            start = end;
        }

        return counter;
    }

    private static boolean isWord(String word){
        char[] chars = word.toCharArray();

        boolean itIsWord = true;

        if(!Character.isAlphabetic(chars[0])){
            return false;
        }

        for (int i = 1; i < chars.length; i++) {
            if(!Character.isAlphabetic(chars[i])){
                for (int j = i; j < chars.length; j++) {

                    if(j < chars.length - 1 &&
                        (Character.isLetterOrDigit(chars[j]) ||
                        chars[j] != '!' ||
                        chars[j] != '?' ||
                        chars[j] != '.')){
                        return false;
                    }
                }
            }
        }

        return itIsWord;
    }


    public static String jsonParser(String json){
        String firstDelimiter = " : ";
	    String secondDelimiter = ", \'";

	    int start = json.indexOf(firstDelimiter) + firstDelimiter.length() + 1;
	    int end = json.indexOf(secondDelimiter, start) - 1;
	    
	    StringBuilder sb = new StringBuilder(json.substring(start, end));
	    
	    start = json.indexOf(firstDelimiter, end);
	    end = json.indexOf(secondDelimiter, start) - 1;
	    sb.append(" ").append(getAverageGrade(start, end, json));
	    
	    start = json.indexOf(firstDelimiter, end);
	    end = json.indexOf(secondDelimiter, start) - 1;
	    sb.append(" ").append(getAverageGrade(start, end, json));
	    
	    return sb.toString();
    }
    private static double getAverageGrade(int start1, int end, String json){
	    
	    char[] grades = json.substring(start1, end).toCharArray();
	    double counter = 0;
	    double sum = 0;
	    
	    for (int i = 0; i < grades.length; i++){
	        if (grades[i] >= '0' && grades[i] <= '5'){
	            counter++;
	            sum += Character.getNumericValue(grades[i]);
	        }
	    }
	    
	    return sum / counter;
    }

    public static ArrayList<ArrayList<Integer>> provideReverseAndRegularList(ArrayList<Integer> list){
	    ArrayList<ArrayList<Integer>> lists = new ArrayList<>(2);
	    
	    list.trimToSize();
	    int size = list.size();
	    
	    ArrayList<Integer> reversedList = new ArrayList(list.size());
	    
	    for (int i = list.size() - 1; i >= 0; i--){
	        reversedList.add(list.get(i));
	    }
	    
	    lists.add(list);
	    lists.add(reversedList);
	    
	    return lists;
	    
	}	

	

}
