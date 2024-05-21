package lesson_05.task_02.model;

import java.util.ArrayList;

import lesson_05.task_02.Animal;
import lesson_05.task_02.Spawnerable;

public class Bear extends Animal implements Spawnerable<Bear>{

    public Bear(String name, int age) {
            super(name, age);
    }

    public Bear(){
        this("BearFactory", 0);
    }

    @Override
    public ArrayList<Bear> spawn(int count) {
        ArrayList<Bear> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add(new Bear("Bear" + i, i));
        }
        return result;
    }
}
