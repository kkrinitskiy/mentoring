package lesson_01;

import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {

//        ЗАДАЧА НА МАССИВЫ

        int[] ints_01 = {100, 90, 80, 70, 60};                      // 5
        int[] ints_02 = {100, 90, 80, 88, -1, -2, -3, -4, -5};      // 6
        int[] ints_03 = {100, 90, 80, 70, 80, -1, -2};              // 4
        int[] ints_04 = {100, 90, 80, 70, 80, -2, -1};              // 4
        int[] ints_05 = {-1, -2, 15, 14, 100, 90, 80, 70, 60};      // 5

        System.out.println(
                getLengthOfNumbersInDecreasingSequence(ints_05)
        );

//        ПЕРВАЯ ЗАДАЧА НА СТРОКИ

        String strTask01_01 = "Какой хороший день";
        String strTask01_02 = "привет мир";
        String strTask01_03 = "Завтра будет день теплее чем был сегодня";

        System.out.println(
                swapWordsInString(strTask01_03)
        );

//        ВТОРАЯ ЗАДАЧА НА СТРОКИ

        String strTask02_01 = "Привет";
        String strTask02_02 = "Piece";
        String strTask02_03 = "Killer423";
        String strTask02_04 = "***Монстр123_!";

        System.out.println(isOnlyLatins(strTask01_01));

//        ВО ВТОРОЙ ЗАДАЧЕ СКАЗАНО ПРО ВВОД С КЛАВИАТУРЫ, ТАК ЧТО ВОТ ОН :)

        while (true) {
            System.out.println("\nВведите слово. \nОно будет проверено на состав. \nЕсли оно состоит только из латинских букв," +
                    "\nто вернется \"YES\", в ином случае \"NO\".\nДля завершения введите stop\n");
            String strFromKeyboard = new Scanner(System.in).nextLine().trim();

            System.out.println("\"" + strFromKeyboard + "\"" + " состоит из букв латинского алфавита: "
                    + "\"" + isOnlyLatins(strFromKeyboard) + "\"");

            if (strFromKeyboard.equalsIgnoreCase("stop")){
                break;
            }
        }


//        ТРЕТЬЯ ЗАДАЧА НА СТРОКИ




    }

    public static int getLengthOfNumbersInDecreasingSequence(int[] ints){

        int result = 0;

        for (int i = 0; i < ints.length - 1; i++) {
            int length = 1;
            for (int j = i; j < ints.length - 1; j++) {
                if(ints[j] > ints[j+1]){
                    length += 1;
                    if (length > result) {
                        result = length;
                        }
                } else {
                    if (length > result) {
                        result = length;
                    }
                    i = j;
                    break;
                }
            }
        }
        return result;
    }

    public static String swapWordsInString(String str){
        String[] words = str.split(" ");

        int numbOfBiggest = 0;
        int numbOfSmallest = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > words[numbOfBiggest].length()){
                numbOfBiggest = i;
            }
            if (words[i].length() <= words[numbOfSmallest].length()){
                numbOfSmallest = i;
            }
        }

        String biggest = words[numbOfBiggest];
        String smallest = words[numbOfSmallest];

        words[numbOfBiggest] = smallest;
        words[numbOfSmallest] = biggest;

        String result = "";
        for (int i = 0; i < words.length; i++) {
            if(i == words.length-1){
                result = result.concat(words[i]);
                break;
            }
            result = result.concat(words[i]).concat(" ");
        }

        return result;
    }

    public static String isOnlyLatins(String str){

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int a = chars[i];
            if (!(a >= 65 && a <= 90) && !(a >= 97 && a <= 122)){
                return "NO";
            }
        }

        return "YES";
    }



}
