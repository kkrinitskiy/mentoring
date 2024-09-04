package data_bases.hw2.task_03;

import data_bases.hw2.task_03.daos.CarDao;
import data_bases.hw2.task_03.daos.CoalDao;
import data_bases.hw2.task_03.daos.TypesOfBodiesDao;
import data_bases.hw2.task_03.daos.WoodDao;
import data_bases.hw2.task_03.entities.*;
import data_bases.hw2.task_03.transportCompanyCore.TransportCompany;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static List<TypesOfBodies> typesOfBodies = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static List<Wood> woods = new ArrayList<>();
    private static List<Coal> coals = new ArrayList<>();

    private static CarDao carDao = new CarDao();
    private static TypesOfBodiesDao typesOfBodiesDao = new TypesOfBodiesDao();
    private static CoalDao coalDao = new CoalDao();
    private static WoodDao woodDao = new WoodDao();

    public static void main(String[] args) {
        initLists();

        TransportCompany company = new TransportCompany(cars.size() + woods.size() + coals.size());


        // список объектов полученный из бд
        List<Transportable> items = new ArrayList<>(cars);
        items.addAll(woods);
        items.addAll(coals);

        // самый дешевый для транспортировки объект
        Transportable cheapestForDelivery = items.stream()
                .min(Comparator.comparingDouble(Transportable::costDelivery))
                .orElse(null);


        List<Transportable> notDeliveredItems = new ArrayList<>();
        for (Transportable item : items) {
            if(!company.loadingItem(item)){
                notDeliveredItems.add(item);
            }
        }

        System.out.println("самый дешевый - " + cheapestForDelivery);

        // список объектов которые нельзя транспортировать из-за размеров
        notDeliveredItems.forEach(System.out::println);

    }


    /**
     * Метод заполняет таблицы данными, затем обновляет списки значениями полученными через hibernate
     */
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
                .width(1800)
                .height(1450)
                .length(4900)
                .build());

        cars.add(Car.builder()
                .mark("Honda")
                .model("CR-V")
                .bodyType(typesOfBodies.get(1))
                .width(1850)
                .height(1680)
                .length(4600)
                .build());

        cars.add(Car.builder()
                .mark("Volkswagen")
                .model("Golf")
                .bodyType(typesOfBodies.get(2))
                .width(1790)
                .height(1450)
                .length(4280)
                .build());

        cars.add(Car.builder()
                .mark("Ford")
                .model("Focus")
                .bodyType(typesOfBodies.get(2))
                .width(1820)
                .height(1470)
                .length(4380)
                .build());

        cars.add(Car.builder()
                .mark("BMW")
                .model("3 Series")
                .bodyType(typesOfBodies.get(0))
                .width(1830)
                .height(1430)
                .length(4710)
                .build());

        cars.add(Car.builder()
                .mark("Mercedes-Benz")
                .model("GLC")
                .bodyType(typesOfBodies.get(1))
                .width(1890)
                .height(1640)
                .length(4660)
                .build());

        cars.add(Car.builder()
                .mark("Audi")
                .model("A4")
                .bodyType(typesOfBodies.get(0))
                .width(1840)
                .height(1430)
                .length(4760)
                .build());

        cars.add(Car.builder()
                .mark("Mazda")
                .model("3")
                .bodyType(typesOfBodies.get(2))
                .width(1800)
                .height(1440)
                .length(4460)
                .build());

        cars.add(Car.builder()
                .mark("Hyundai")
                .model("Tucson")
                .bodyType(typesOfBodies.get(1))
                .width(1850)
                .height(1650)
                .length(4480)
                .build());

        cars.add(Car.builder()
                .mark("Kia")
                .model("Optima")
                .bodyType(typesOfBodies.get(0))
                .width(1860)
                .height(1470)
                .length(4860)
                .build());


        for (Car car : cars) {
            carDao.add(car);
        }

        cars = carDao.getAll();

        woods.add(Wood.builder()
                .width(1500)
                .height(800)
                .length(3000)
                .build());

        woods.add(Wood.builder()
                .width(2000)
                .height(1200)
                .length(4500)
                .build());

        woods.add(Wood.builder()
                .width(1800)
                .height(1000)
                .length(3500)
                .build());

        woods.add(Wood.builder()
                .width(2200)
                .height(1500)
                .length(5000)
                .build());

        woods.add(Wood.builder()
                .width(1700)
                .height(900)
                .length(4000)
                .build());

        for (Wood wood : woods) {
            woodDao.add(wood);
        }

        woods = woodDao.getAll();

        coals.add(Coal.builder()
                .width(1200)
                .height(700)
                .length(2500)
                .build());

        coals.add(Coal.builder()
                .width(1500)
                .height(900)
                .length(3000)
                .build());

        coals.add(Coal.builder()
                .width(1000)
                .height(600)
                .length(2000)
                .build());

        coals.add(Coal.builder()
                .width(1800)
                .height(1100)
                .length(3500)
                .build());

        coals.add(Coal.builder()
                .width(1300)
                .height(800)
                .length(2800)
                .build());

        for (Coal coal : coals) {
            coalDao.add(coal);
        }

        coals = coalDao.getAll();

    }
}
