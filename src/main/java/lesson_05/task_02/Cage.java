package lesson_05.task_02;

import java.util.ArrayList;
import java.util.List;

public class Cage<T extends Animal> {
    List<T> placesInCage;
    private int countOfPlacesInCage;

    public Cage(int countOfPlacesInCage) {
        placesInCage = new ArrayList<>();
        this.countOfPlacesInCage = countOfPlacesInCage;
    }

    public boolean addAnimal(T animal){
        if(isFreePlaces()){
            placesInCage.add(animal);
            return true;
        }
        return false;
    }
    
    public boolean isFreePlaces(){
       return placesInCage.size() < countOfPlacesInCage;
    }

    public T getAnimalByName(String name){
       for (T t : placesInCage) {
        if(t.getName().equals(name)){
            return t;
        }
       }

       return null;
    }

    public T takeOutAnimal(String name){
        T t = getAnimalByName(name);
        placesInCage.remove(t);
        return t;
    }



    public boolean transferAnimal(Cage<T> newCage, String name){
       T t = takeOutAnimal(name);
       if(newCage.addAnimal(t)){
        return true;
       }
       return false;
    }

    public List<T> getAnimalList() {
        return placesInCage;
    }

     

}
