package lesson_05.task_02.model;

import java.util.ArrayList;

import lesson_05.task_02.Animal;

public class Bear extends Animal {

    public Bear(String name, int age) {
        super(name, age);
    }

    public static ArrayList<Bear> spawn(int count){
        ArrayList<Bear> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add(new Bear("Bear" + i, i));
        }
        return result;
    }
}
