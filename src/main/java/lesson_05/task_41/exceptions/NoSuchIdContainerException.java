package lesson_05.task_41.exceptions;

public class NoSuchIdContainerException extends Exception{
    public NoSuchIdContainerException(){
        super("There is no container with such id");
    }
}
