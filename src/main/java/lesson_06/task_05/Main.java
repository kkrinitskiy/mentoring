package lesson_06.task_05;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<ArrayList<String>, String> findBiggestString = new Function<ArrayList<String>, String>() {
            @Override
            public String apply(ArrayList<String> strings) {
                String maxString = "";
                for (String string : strings) {
                    if(string.length() > maxString.length()){
                        maxString = string;
                    }
                }
                return maxString;
            }
        };

        ArrayList<String> stringsList = new ArrayList<>(List.of("Перввый","Второй","Длинное словечко","Окончание"));
        findBiggestString.apply(stringsList);
    }
}
