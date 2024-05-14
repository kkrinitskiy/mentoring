package lesson_05.task_02.model;

import java.util.ArrayList;

import lesson_05.task_02.Animal;
import lesson_05.task_02.Spawnerable;

public class Dolphin extends Animal implements Spawnerable<Dolphin>{

    public Dolphin(String name, int age) {
        super(name, age);    
    }

    public Dolphin() {
        this("", 0);    
    }

    public ArrayList<Dolphin> spawn(int count){
        ArrayList<Dolphin> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add(new Dolphin("Dolphin" + i, i));
        }
        return result;
    }


    
}
