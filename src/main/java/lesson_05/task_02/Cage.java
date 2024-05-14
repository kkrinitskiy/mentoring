package lesson_05.task_02;

import java.util.ArrayList;
import java.util.List;

import lesson_05.task_02.exceptions.NoAnimalWithSuchNameException;
import lesson_05.task_02.exceptions.NoAnimalsInCageException;

public class Cage<T extends Animal> {
    private List<T> placesInCage;
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

    public int getNumberFreePlaces(){
        return countOfPlacesInCage - placesInCage.size();
    }     

    public T getOldestOne() throws NoAnimalsInCageException{
        if(!placesInCage.isEmpty()){
            T oldest = placesInCage.get(0);
            for (T t : placesInCage) {
                if(t.getAge() > oldest.getAge()){
                    oldest = t;
                }
            }
            return oldest;
        }
        throw new NoAnimalsInCageException("You can't get the OLDEST animal from empty cage!");
    }

    public T getYoungestOne() throws NoAnimalsInCageException{
        if(!placesInCage.isEmpty()){
            T youngest = placesInCage.get(0);
            for (T t : placesInCage) {
                if(t.getAge() < youngest.getAge()){
                    youngest = t;
                }
            }
            return youngest;
        }
        throw new NoAnimalsInCageException("You can't get the YOUNGEST animal from empty cage!");
    }

}
