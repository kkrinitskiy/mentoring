package lesson_05.task_41;

public abstract class Item {
    
    private int width;
    private int length;
    private int height;

    public Item(int width, int length, int height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public int getVolume(){
        return width*length*height;
    }

    
}
