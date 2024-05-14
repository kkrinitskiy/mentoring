package lesson_05.task_41;

import java.util.*;

import lesson_05.task_41.models.Car;
import lesson_05.task_41.models.Container;
import lesson_05.task_41.models.ContainerShip;

public class Main {
    public static void main(String[] args) {
        ContainerShip cs = new ContainerShip(10);

        List<Container<Car>> containers = new Container<Car>().factory(10, 2000, 5000, 2000);
        
        containers.get(0).loadItem(new Car("null", "null", 2000, 2000, 2000));

        
    }
}
