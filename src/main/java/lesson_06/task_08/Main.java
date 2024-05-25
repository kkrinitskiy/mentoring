package lesson_06.task_08;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, String> removeExtraSpaces = s -> {
            if(s.length() == 0 || s == null){
                return "";
            }
            return s.strip().replaceAll("\\s{2,}"," ");
        };

        System.out.println(removeExtraSpaces.apply("Мне   16 лет  "));
    }
}
