package lesson_05.task_02;

import java.util.List;

import lesson_05.task_02.exceptions.NoAnimalWithSuchNameException;
import lesson_05.task_02.model.*;

public class Main {
    public static void main(String[] args) {
        

        // Для реализации фабричного метода у каждого класса имплементирующего интерфейс Spawnerable
        // пришлось везде добавить конструктор без параметров, поскольку статические методы не могут наследоваться и переопределяться
        List<Snake> snakes = new Snake().spawn(10);
        List<Dolphin> dolphins = new Dolphin().spawn(10);
        List<Bear> bears = new Bear().spawn(10);

        Cage<Snake> snakeCage = new Cage<>(10);
        Cage<Dolphin> dolphinCage = new Cage<>(10);
        Cage<Bear> bearCage = new Cage<>(10);

        putAnimalsInCage(snakeCage, snakes);
        putAnimalsInCage(dolphinCage, dolphins);
        putAnimalsInCage(bearCage, bears);
        
        Cage<Snake> snakeCage2 = new Cage<>(4);
        snakeCage2.addAnimal(new Snake("OldestSnake", 99));
        snakeCage2.addAnimal(new Snake("RegularSnake", 12));
        snakeCage2.addAnimal(new Snake("YoungestSnake", -1));

        Cage<Snake> snakeCage3 = new Cage<>(4);
        snakeCage3.addAnimal(new Snake("OldestSnake", 150));
        snakeCage3.addAnimal(new Snake("RegularSnake", 12));
        snakeCage3.addAnimal(new Snake("YoungestSnake", -20));

        // берем самую старую змею из snakeCage2 и меняем ее местами с самой молодой из snakeCage3
        System.out.println(snakeCage2.getAnimalList().size()); // 3 змеи в snakeCage2
        System.out.println(snakeCage2.getAnimalList());
        try{
        snakeCage2.transferAnimal(snakeCage3, getOldestFromCage(snakeCage2).getName()); // самая старая змея из snakeCage2 уехала в snakeCage3
        } catch(NoAnimalWithSuchNameException e){
            e.printStackTrace();
        }
        System.out.println(snakeCage2.getAnimalList().size()); // 2 змеи в snakeCage2

        try{
        snakeCage3.transferAnimal(snakeCage2, getYoungestFromCage(snakeCage3).getName());  // самая молодая змея из snakeCage3 уехала в snakeCage2
        } catch (NoAnimalWithSuchNameException e){
            e.printStackTrace();
        }
        System.out.println(snakeCage2.getAnimalList().size()); // снова 3 змеи в snakeCage2
        System.out.println(snakeCage2.getAnimalList()); // задокументирован обмен змей



    }

    public static <T extends Animal> boolean putAnimalsInCage(Cage<T> cage, List<T> animals){
        if(cage.freePlaces() - animals.size() >= 0){
            for (T animal : animals){
                cage.addAnimal(animal);
            }
            return true;
        }
        return false;

    }

    public static <T extends Animal> T getOldestFromCage(Cage<T> cage){
        T t = cage.getAnimalList().get(0);
        for (T animal : cage.getAnimalList()) {
            if(animal.getAge() > t.getAge()){
                t = animal;
            }
        }
        return t;
    }

    public static <T extends Animal> T getYoungestFromCage(Cage<T> cage){
        T t = cage.getAnimalList().get(0);
        for (T animal : cage.getAnimalList()) {
            if(animal.getAge() < t.getAge()){
                t = animal;
            }
        }
        return t;
    }

}
