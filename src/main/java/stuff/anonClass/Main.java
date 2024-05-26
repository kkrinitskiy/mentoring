package stuff.anonClass;


//## Задание: "Фабрика эмоций"
//
//        **Цель:** Попрактиковаться в создании и использовании анонимных классов в Java без реализации существующих интерфейсов.
//
//**Описание:**
//
//        Напишите программу, которая симулирует "фабрику эмоций".
//
//        1. **Создайте абстрактный класс `Emotion` с абстрактным методом `express()`, который не принимает аргументов и возвращает `String`.**
//
//        2. **В методе `main` создайте массив `emotions` типа `Emotion[]`, который будет хранить 5 различных эмоций.**
//
//        3. **Используя анонимные классы, создайте 5 различных эмоций, каждая из которых будет переопределять метод `express()` для вывода своего названия и краткого описания.**
//
//        * Например:
//        * Радость: "Я радость! Я делаю мир ярче!"
//        * Грусть: "Я грусть... Мне немного одиноко."
//        * Злость: "Я злость! Берегитесь!!!"
//        * Удивление: "Я удивление! Вот это поворот!"
//        * Спокойствие: "Я спокойствие. Все под контролем."
//
//        4. **Поместите созданные эмоции в массив `emotions`.**
//
//        5. **Пройдитесь по массиву `emotions` и для каждой эмоции вызовите метод `express()`, выводя результат на консоль.**
//
//        **Пример вывода:**
//
//        ```
//Я радость! Я делаю мир ярче!
//Я грусть... Мне немного одиноко.
//Я злость! Берегитесь!!!
//Я удивление! Вот это поворот!
//Я спокойствие. Все под контролем.
//```
//
//        **Дополнительно:**
//
//        * Попробуйте добавить в абстрактный класс `Emotion` дополнительные поля, например, `intensity` (интенсивность) типа `int`, и используйте их в анонимных классах для более разнообразного описания эмоций.
//* Модифицируйте программу, чтобы пользователь мог вводить название эмоции, а программа создавала бы соответствующую эмоцию с помощью анонимного класса и выводила её описание.
//
//
//Это задание поможет вам разобраться с созданием анонимных классов "на лету" и понять, как использовать их для решения задач, где требуется создать объекты классов с уникальной функциональностью без необходимости объявлять эти классы отдельно.

import java.text.MessageFormat;
import java.util.Scanner;

public class Main {

    public static final String[] emos = new String[]{"Я радость! Я делаю мир ярче!",
            "Я грусть... Мне немного одиноко.", "Я злость! Берегитесь!!!",
            "Я удивление! Вот это поворот!", "Я спокойствие. Все под контролем."};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmosNames emosNameInput = null;
        int inputIntensity = 0;

        System.out.println("""
                Введите номер эмоции:\

                0 - радость\

                1 - грусть\

                2 - злость\

                3 - удивление\

                4 - спокойствие\n""");


        boolean inputIsOk = false;
        while (!inputIsOk) {
            switch (sc.next()) {
                case "0" -> {
                    emosNameInput = EmosNames.JOY;
                    inputIsOk = true;
                }
                case "1" -> {
                    emosNameInput = EmosNames.SADNESS;
                    inputIsOk = true;
                }
                case "2" -> {
                    emosNameInput = EmosNames.ANGER;
                    inputIsOk = true;
                }
                case "3" -> {
                    emosNameInput = EmosNames.SURPRISE;
                    inputIsOk = true;
                }
                case "4" -> {
                    emosNameInput = EmosNames.CALM;
                    inputIsOk = true;
                }
                case null, default -> {
                    System.out.println("повторите ввод\n");
                }

            }
        }

        System.out.println("Введите интенсивность от 1 до 10\n");
        inputIsOk = false;
        while (!inputIsOk) {
          String input = sc.next().strip();
          if(input.chars().anyMatch(ch -> Character.isDigit(ch)
          && Integer.parseInt(input) >= 0
          && Integer.parseInt(input) <= 10)){
             inputIntensity = Integer.parseInt(input);
             inputIsOk = true;
            } else {
              System.out.println("повторите ввод\n");
          }
        }

        //////////////////////////////// КОСТЫЛИ!!!!!!!!!!!!!!!!!
        int a = inputIntensity;
        EmosNames b = emosNameInput;
        //////////////////////////////////////////////////////////

        Emotion e = new Emotion() {
            EmosNames eName = b;
            int intensity = a;

            @Override
            public String express() {
                return MessageFormat.format("[{0}] \"{1}\" \t[Интенсивность:{2}]" , eName.name, eName.message, intensity);
            }
        };


        System.out.println(e.express());


    }

    enum EmosNames {
        JOY("Радость", emos[0]), SADNESS("Грусть", emos[1]),
        ANGER("Злость", emos[2]), SURPRISE("Удивление", emos[3]),
        CALM("Спокойствие", emos[4]);

        String name;
        String message;

        EmosNames(String name, String message) {
            this.name = name;
            this.message = message;
        }
    }

}





