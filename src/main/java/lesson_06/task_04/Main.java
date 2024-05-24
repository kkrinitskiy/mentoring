package lesson_06.task_04;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<String> isPalindrome = a -> {
            for (int i = 0; i < a.length(); i++) {
                if(a.charAt(i) != a.charAt(a.length() - 1 - i)){
                    return false;
                }
            }
            return true;
        };

        List<String> strings = Arrays.asList("доход", "мясо", "мадам");

        printLargestPalindrome(strings, isPalindrome);

    }

    public static void printLargestPalindrome(List<String> strings, Predicate<String> p){
        String result = "";
        for (int i = 0; i < strings.size(); i++) {
            if(p.test(strings.get(i)) && strings.get(i).length() > result.length()){
                result = strings.get(i);
            }
        }
        System.out.println(result);
    }

}
