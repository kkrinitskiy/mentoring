package lesson_05.task_02.model;

import java.util.ArrayList;

import lesson_05.task_02.Animal;
import lesson_05.task_02.Spawnerable;


public class Snake extends Animal implements Spawnerable {

    public Snake(String name, int age) {
        super(name, age);
    }

    public Snake() {
        this("", 0);
    }
    
    public ArrayList<Snake> spawn(int count){
        ArrayList<Snake> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add(new Snake("Snake" + i, i));
        }
        return result;
    }
}
