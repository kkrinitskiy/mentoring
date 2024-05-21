package lesson_05.task_04.models;

import lesson_05.task_04.Treasure;

public class Diamonds extends Treasure {

    public Diamonds(int quantity) {
        super(quantity, false);
    }

    @Override
    public int getPrice() {
        return getQuantity() * 500;
    }
    
}
