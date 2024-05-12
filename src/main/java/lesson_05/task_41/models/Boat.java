package lesson_05.task_41.models;

import java.util.ArrayList;
import java.util.List;

import lesson_05.task_41.Item;

public class Boat extends Item {

    private final String name;

    public Boat(String name, int width, int length, int height) {
        super(width, length, height);
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static List<Boat> factory(int containersCount, String name, int width, int length, int height){
        List<Boat> result = new ArrayList<>();
        for (int i = 0; i < containersCount; i++) {
            result.add(new Boat(name, width, length, height));
        }
        return result;
    }
}
