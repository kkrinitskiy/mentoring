package lesson_05.task_02.model;

import java.util.ArrayList;

import lesson_05.task_02.Animal;


public class Snake extends Animal {

    public Snake(String name, int age) {
        super(name, age);
    }
    
    public static ArrayList<Snake> spawn(int count){
        ArrayList<Snake> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add(new Snake("Snake" + i, i));
        }
        return result;
    }
}
