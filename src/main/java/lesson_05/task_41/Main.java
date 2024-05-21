package lesson_05.task_41;

import java.util.*;

import lesson_05.task_41.exceptions.DimensionLessThenOneException;
import lesson_05.task_41.exceptions.EmptyContainerShip;
import lesson_05.task_41.exceptions.NotEnoughContainers;
import lesson_05.task_41.exceptions.NotEnoughFreePlacesOnShip;
import lesson_05.task_41.models.Boat;
import lesson_05.task_41.models.Car;
import lesson_05.task_41.models.Container;
import lesson_05.task_41.models.ContainerShip;

public class Main {
    public static void main(String[] args) throws DimensionLessThenOneException, NotEnoughContainers, NotEnoughFreePlacesOnShip, EmptyContainerShip {

        ContainerShip cs = new ContainerShip(10);

        
        List<Container<Car>> carContainers = new Container<Car>().factory(5, 200, 500, 200);
        List<Container<Boat>> boatContainers = new Container<Boat>().factory(5, 200, 500, 200);

        putItemsIntoContainers(carContainers, Car.factory(3, "sdfsdf", "sdfsdfds", 200, 300, 100));
        carContainers.get(3).loadItem(new Car("first", "first", 200, 500, 200));
        carContainers.get(4).loadItem(new Car("second", "second", 200, 450, 200));

        putItemsIntoContainers(boatContainers, Boat.factory(3, "null", 200, 300, 100));
        boatContainers.get(3).loadItem(new Boat("third", 200, 400, 200));
        boatContainers.get(4).loadItem(new Boat("fourth", 200, 350, 200));
        
        loadContainersOnShip(cs, carContainers);
        loadContainersOnShip(cs, boatContainers);

        cs.getContainersListSortedByVolume(4).forEach(c -> {System.out.println(c.getItemVolume());});
    }



    public static <T extends Item> void putItemsIntoContainers(List<Container<T>> containersList, List<T> itemsList) throws NotEnoughContainers{
        if(itemsList.size() <= containersList.size()){
            for (int i = 0; i < itemsList.size(); i++) {
                containersList.get(i).loadItem(itemsList.get(i));
            }
        }else{
            throw new NotEnoughContainers("itemsList exceeds containersList");
        }
    }

    public static <T extends Item> void loadContainersOnShip(ContainerShip cs, List<Container<T>> containersList) throws NotEnoughFreePlacesOnShip{
        if(containersList.size() <= cs.getFreePlaces()){
            for (int i = 0; i < containersList.size(); i++) {
                cs.loadContainer(containersList.get(i));
            }
        }else{
            throw new NotEnoughFreePlacesOnShip("ContainersList exceeds free places in ContainerShip");
        }
    }
}

