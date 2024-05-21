package lesson_05.task_01;

import java.util.*;

public class PrintClass<T> {
    private final List<T> values; 

    public PrintClass() {
        values = new ArrayList<>();
    }

    public void printAll(){
        for (T t : values) {
            System.out.println(t);
        }
    }

    public void print(int i){
        System.out.println(values.get(i));
    }
    
}
