package lesson_05.task_02;

import java.util.ArrayList;
import java.util.List;

import lesson_05.task_02.exceptions.NoAnimalWithSuchNameException;

public class Cage<T extends Animal> /*implements Spawnerable<Cage<T>>*/{
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

    public T getAnimalByName(String name) throws NoAnimalWithSuchNameException{
       for (T t : placesInCage) {
        if(t.getName().equals(name)){
            return t;
        }
       }
       throw new NoAnimalWithSuchNameException(name);
    }

    public T takeOutAnimal(String name) throws NoAnimalWithSuchNameException{
        T t = getAnimalByName(name);
        placesInCage.remove(t);
        return t;
    }



    public boolean transferAnimal(Cage<T> newCage, String name) throws NoAnimalWithSuchNameException{
       T t = takeOutAnimal(name);
       if(newCage.addAnimal(t)){
        return true;
       }
       return false;
    }

    public List<T> getAnimalList() {
        return placesInCage;
    }

    public int freePlaces(){
        return countOfPlacesInCage - placesInCage.size();
    }

    // @Override
    // public lesson_05.task_02.ArrayList<Cage<T>> spawn() {
        
    //     throw new UnsupportedOperationException("Unimplemented method 'spawn'");
    // }

    

     

}
