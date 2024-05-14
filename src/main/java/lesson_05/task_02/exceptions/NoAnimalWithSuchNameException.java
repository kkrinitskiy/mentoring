package lesson_05.task_02.exceptions;

public class NoAnimalWithSuchNameException extends Exception {
    public NoAnimalWithSuchNameException(String name){
        super("There is no animal with name: " + name);
    }
}
