package lesson_04;

import java.util.ArrayList;

import lesson_04.item_models.*;
import lesson_04.transport_company_core.*;
import lesson_04.item_models.Car;
import lesson_04.item_models.Elephant;
import lesson_04.item_models.Panzer;
import lesson_04.transport_company_core.TransportCompany;
import lesson_04.transport_company_core.Transportable;

import java.util.*;

import static lesson_04.transport_company_core.Tax.*;

public class Main {
    public static void main(String[] args) {

        TransportCompany yandexDostavka = new TransportCompany(10);

        List<Transportable> items = new ArrayList<>();
        List<Transportable> itemsWithoutContainer = new ArrayList<>();

        Car car_00 = new Car(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), CAR.getRate());
        Car car_01 = new Car(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), CAR.getRate());
        Car car_02 = new Car(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), CAR.getRate());
        items.add(car_00);
        items.add(car_01);
        items.add(car_02);
      
        Elephant elephant_00 = new Elephant(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), ANIMAL.getRate());
        Elephant elephant_01 = new Elephant(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), ANIMAL.getRate());
        Elephant elephant_02 = new Elephant(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), ANIMAL.getRate());
        items.add(elephant_00);
        items.add(elephant_01);
        items.add(elephant_02);

        Panzer panzer_00 = new Panzer(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), WEAPON.getRate());
        Panzer panzer_01 = new Panzer(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), WEAPON.getRate());
        Panzer panzer_02 = new Panzer(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), WEAPON.getRate());
        
        // гарантированно неподходящий танк для проверки
        Panzer panzer_03 = new Panzer(600, HWUtils.getRandomHight(), HWUtils.getRandomLength(), WEAPON.getRate());
        items.add(panzer_00);
        items.add(panzer_01);
        items.add(panzer_02);
        items.add(panzer_03);

        getCheapestTransportable(items);

        for (Transportable item : items) {
            if(!yandexDostavka.loadingItem(item)){
                itemsWithoutContainer.add(item);
            }
        }   

        System.out.println("Список тех кому не нашелся контейнер:");
        System.out.println(itemsWithoutContainer);


    }



    public static void getCheapestTransportable(List<Transportable> list){
        Transportable item = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i).costDelivery() < item.costDelivery()){
                item = list.get(i);
            }
        }
        System.out.println(
            "\nСамый дешевый: " + item.toString());

        System.out.println("\nВсе:");
        list.forEach(i -> System.out.println(i));
    }


}
