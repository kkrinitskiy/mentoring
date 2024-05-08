package lesson_04.hw4;

import java.util.ArrayList;
import java.util.Random;

import lesson_04.hw4.item_models.Car;
import lesson_04.hw4.item_models.Elephant;
import lesson_04.hw4.item_models.Panzer;
import lesson_04.hw4.transport_company_core.Container;
import lesson_04.hw4.transport_company_core.TransportCompany;
import lesson_04.hw4.transport_company_core.Transportable;

import java.text.MessageFormat;
import java.util.*;

public class Main {
    public static final double CAR_TAX = 2.4;
    public static final double ANIMAL_TAX = 3.6;
    public static final double WEAPON_TAX = 2.9;
    public static void main(String[] args) {

        TransportCompany yandexDostavka = new TransportCompany(10);

        List<Transportable> items = new ArrayList<>();

        Car car_00 = new Car(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), CAR_TAX);
        Car car_01 = new Car(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), CAR_TAX);
        Car car_02 = new Car(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), CAR_TAX);
        items.add(car_00);
        items.add(car_01);
        items.add(car_02);
      
        Elephant elephant_00 = new Elephant(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), ANIMAL_TAX);
        Elephant elephant_01 = new Elephant(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), ANIMAL_TAX);
        Elephant elephant_02 = new Elephant(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), ANIMAL_TAX);
        items.add(elephant_00);
        items.add(elephant_01);
        items.add(elephant_02);

        Panzer panzer_00 = new Panzer(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), WEAPON_TAX);
        Panzer panzer_01 = new Panzer(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), WEAPON_TAX);
        Panzer panzer_02 = new Panzer(HWUtils.getRandomWidth(), HWUtils.getRandomHight(), HWUtils.getRandomLength(), WEAPON_TAX);
        Panzer panzer_03 = new Panzer(600, HWUtils.getRandomHight(), HWUtils.getRandomLength(), WEAPON_TAX);
        items.add(panzer_00);
        items.add(panzer_01);
        items.add(panzer_02);
        items.add(panzer_03);

        getCheapestTransportable(items);

        for (Transportable item : items) {
            yandexDostavka.loadingItem(item);
        }


    }



    public static void getCheapestTransportable(List<Transportable> list){
        Transportable item = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i).costDelivery() < item.costDelivery()){
                item = list.get(i);
            }
        }
        System.out.println(
            MessageFormat.format(
                "\nСамый дешевый: \n{0}, с габаритами {1}x{2}x{3}, доставка стоит: {4}", 
                item.getClass().getSimpleName(), 
                item.getWidth(), 
                item.getHight(), 
                item.getLength(),
                item.costDelivery()));

        System.out.println("\nОсатльные:");
        list.forEach(i -> System.out.println(
            MessageFormat.format(
                "{0}, с габаритами: {1}x{2}x{3}, доставка стоит: {4}", 
                i.getClass().getSimpleName(), 
                i.getWidth(), 
                i.getHight(), 
                i.getLength(),
                i.costDelivery())));
    }


}
