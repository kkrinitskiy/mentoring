package lesson_06.task_16;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Function<String, Integer> getCountOfAInWord = str -> {
            if(str.isBlank() || str == null){
                return 0;
            }
            int counter = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.toLowerCase().charAt(i) == 'a'
                        || str.toLowerCase().charAt(i) == 'а'){
                    counter++;
                }
            }
            return counter;

        };

        List<String> list = Arrays.asList("кот", "дом", "солнце", "дерево", "облако", "программа", "компьютер", "монитор", "клавиатура", "наушники", "телефон", "интернет", "сообщение", "диалог", "информация", "алгоритм", "технология", "разработка", "приложение", "инновация");

        String wordWithMaxCountOfA = list.stream()
                .reduce((a, b) -> getCountOfAInWord.apply(a) > getCountOfAInWord.apply(b) ? a : b)
                .get();

        System.out.println(wordWithMaxCountOfA);
    }

}
