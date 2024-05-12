package lesson_05.task_02.model;

import java.util.ArrayList;

import lesson_05.task_02.Animal;

public class Dolphin extends Animal{

    public Dolphin(String name, int age) {
        super(name, age);    
    }

     public static ArrayList<Dolphin> spawn(int count){
        ArrayList<Dolphin> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add(new Dolphin("Dolphin" + i, i));
        }
        return result;
    }
    
}
