package lesson_05.task_02;

import java.util.ArrayList;

public interface Spawnerable<T> {
    ArrayList<T> spawn(int count);

    public static void someMethod(){};
}
