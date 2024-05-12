package lesson_05.task_41.models;

import java.util.ArrayList;
import java.util.List;

import lesson_05.task_41.Item;

public class Car extends Item{

    private final String mark;
    private final String model;

    public Car(String mark, String model, int width, int length, int height) {
        super(width, length, height);
        this.mark = mark;
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }
    
    public static List<Car> factory(int containersCount, String mark, String model, int width, int length, int height){
        List<Car> result = new ArrayList<>();
        for (int i = 0; i < containersCount; i++) {
            result.add(new Car(mark, model, width, length, height));
        }
        return result;
    }
}
