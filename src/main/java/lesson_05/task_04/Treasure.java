package lesson_05.task_04;

public abstract class Treasure {
    private final int quantity;
    private final boolean isEmpty;

    public Treasure(int quantity, boolean isEmpty) {
        this.quantity = quantity;
        this.isEmpty = isEmpty;
    }

    public int getQuantity() {
        return quantity;
    }
    

    public abstract int getPrice();

    public boolean isEmpty() {
        return isEmpty;
    }
    
}
