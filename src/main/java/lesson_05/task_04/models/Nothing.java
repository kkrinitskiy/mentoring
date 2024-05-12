package lesson_05.task_04.models;

import lesson_05.task_04.Treasure;

public class Nothing extends Treasure{

    public Nothing() {
        super(0, true);
    }

    @Override
    public int getPrice() {
        return 0;
    }
    
}
