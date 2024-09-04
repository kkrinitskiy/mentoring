package data_bases.hw2.task_03;

import data_bases.hw2.task_03.daos.CarDao;
import data_bases.hw2.task_03.daos.CoalDao;
import data_bases.hw2.task_03.daos.TypesOfBodiesDao;
import data_bases.hw2.task_03.daos.WoodDao;
import data_bases.hw2.task_03.entities.Car;
import data_bases.hw2.task_03.entities.TypesOfBodies;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<TypesOfBodies> typesOfBodies = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static CarDao carDao = new CarDao();
    private static TypesOfBodiesDao typesOfBodiesDao = new TypesOfBodiesDao();
    private static CoalDao coalDao = new CoalDao();
    private static WoodDao woodDao = new WoodDao();

    public static void main(String[] args) {
        initLists();


    }

    private static void initLists() {
        typesOfBodies.add(TypesOfBodies.builder()
                .type("Sedan")
                .costDelivery(100.0)
                .build());

        typesOfBodies.add(TypesOfBodies.builder()
                .type("SUV")
                .costDelivery(150.0)
                .build());

        typesOfBodies.add(TypesOfBodies.builder()
                .type("Hatchback")
                .costDelivery(120.0)
                .build());

        for (TypesOfBodies typeOfBodies : typesOfBodies) {
            typesOfBodiesDao.add(typeOfBodies);
        }

        typesOfBodies = typesOfBodiesDao.getAll();

        cars.add(Car.builder()
                .mark("Toyota")
                .model("Camry")
                .bodyType(typesOfBodies.get(0))
                .width(1.8)
                .height(1.45)
                .length(4.9)
                .build());

        cars.add(Car.builder()
                .mark("Honda")
                .model("CR-V")
                .bodyType(typesOfBodies.get(1))
                .width(1.85)
                .height(1.68)
                .length(4.6)
                .build());

        cars.add(Car.builder()
                .mark("Volkswagen")
                .model("Golf")
                .bodyType(typesOfBodies.get(2))
                .width(1.79)
                .height(1.45)
                .length(4.28)
                .build());

        cars.add(Car.builder()
                .mark("Ford")
                .model("Focus")
                .bodyType(typesOfBodies.get(2))
                .width(1.82)
                .height(1.47)
                .length(4.38)
                .build());

        cars.add(Car.builder()
                .mark("BMW")
                .model("3 Series")
                .bodyType(typesOfBodies.get(0))
                .width(1.83)
                .height(1.43)
                .length(4.71)
                .build());

        cars.add(Car.builder()
                .mark("Mercedes-Benz")
                .model("GLC")
                .bodyType(typesOfBodies.get(1))
                .width(1.89)
                .height(1.64)
                .length(4.66)
                .build());

        cars.add(Car.builder()
                .mark("Audi")
                .model("A4")
                .bodyType(typesOfBodies.get(0))
                .width(1.84)
                .height(1.43)
                .length(4.76)
                .build());

        cars.add(Car.builder()
                .mark("Mazda")
                .model("3")
                .bodyType(typesOfBodies.get(2))
                .width(1.80)
                .height(1.44)
                .length(4.46)
                .build());

        cars.add(Car.builder()
                .mark("Hyundai")
                .model("Tucson")
                .bodyType(typesOfBodies.get(1))
                .width(1.85)
                .height(1.65)
                .length(4.48)
                .build());

        cars.add(Car.builder()
                .mark("Kia")
                .model("Optima")
                .bodyType(typesOfBodies.get(0))
                .width(1.86)
                .height(1.47)
                .length(4.86)
                .build());

        for (Car car : cars) {
            carDao.add(car);
        }


    }
}
