package lesson_06.task_19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Main {

    private static final double CURRENT_EXCHANGE_RATES_ROUBLES_IN_DOLLAR = 91.63;
    private static final List<Map<String,String>> EMPLOYEE_LIST = new ArrayList<>(List.of(
            new HashMap<String,String>(Map.of("Имя", "Кирилл", "Возраст", "26", "Должность", "Инженер", "Зарплата", "150000 руб")),
            new HashMap<String,String>(Map.of("Имя", "Виталий", "Возраст", "28", "Должность", "Директор", "Зарплата", "2000 $")),
            new HashMap<String,String>(Map.of("Имя", "Александр", "Возраст", "31", "Должность", "Бухгалтер", "Зарплата", "50000 руб")),
            new HashMap<String,String>(Map.of("Имя", "Дементий", "Возраст", "35", "Должность", "Старший инженер", "Зарплата", "1500 $")),
            new HashMap<String,String>(Map.of("Имя", "Емельян", "Возраст", "28", "Должность", "Программист", "Зарплата", "256000 руб")),
            new HashMap<String,String>(Map.of("Имя", "Федор", "Возраст", "20", "Должность", "Сварщик", "Зарплата", "35000"))
    ));

    public static void main(String[] args) {

//        System.out.println(EMPLOYEE_LIST.get(0).get("Имя"));
        System.out.println("Младше 30:");
        EMPLOYEE_LIST.stream().filter(a -> Integer.parseInt(a.get("Возраст")) < 30).map(a -> a.get("Имя")).forEach(System.out::println);

        System.out.println("\nЗп в рублях:");
        EMPLOYEE_LIST.stream().filter(a -> a.get("Зарплата").contains("руб")).map(a -> a.get("Имя")).forEach(System.out::println);

        System.out.println("\nСредний возраст всех сотрудников:"
                + EMPLOYEE_LIST.stream()
                .map(a -> Integer.parseInt(a.get("Возраст")))
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble());

        System.out.println("\nИмя и возраст с самой большой зп: ");
        EMPLOYEE_LIST.stream().reduce((a, b) ->
                {
                    double aSalary = a.get("Зарплата").contains("$") ?
                            Integer.parseInt(a.get("Зарплата").split(" ")[0]) * CURRENT_EXCHANGE_RATES_ROUBLES_IN_DOLLAR :
                            Integer.parseInt(a.get("Зарплата").split(" ")[0]);

                    double bSalary = b.get("Зарплата").contains("$") ?
                            Integer.parseInt(b.get("Зарплата").split(" ")[0]) * CURRENT_EXCHANGE_RATES_ROUBLES_IN_DOLLAR :
                            Integer.parseInt(b.get("Зарплата").split(" ")[0]);

                    return aSalary > bSalary ? a : b;
                }
                ).get().forEach((a, b)-> {
                    if(a.equals("Имя") || a.equals("Возраст")){
                        System.out.print(b + " ");
                    }
                });

    }
}
