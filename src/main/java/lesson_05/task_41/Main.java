package lesson_05.task_41;

import java.util.*;

import lesson_05.task_41.models.Car;
import lesson_05.task_41.models.Container;
import lesson_05.task_41.models.ContainerShip;

public class Main {
    public static void main(String[] args) {
        ContainerShip cs = new ContainerShip(10);

        List<Container<? extends Item>> containers = Container.factory(10, 2000, 5000, 2000);
        Car car = new Car("asd", "asd", 1000, 1000, 1000);
       
    }
}
