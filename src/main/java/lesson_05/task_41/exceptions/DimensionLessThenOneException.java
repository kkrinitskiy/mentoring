package lesson_05.task_41.exceptions;

/**
 * DimensionLessThenOneException
 */
public class DimensionLessThenOneException extends Exception {
    public DimensionLessThenOneException(){
        super("No dimension of item cam be less than 1!");
    }
}
