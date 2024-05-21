package lesson_05.task_41;

import lesson_05.task_41.exceptions.DimensionLessThenOneException;

public abstract class Item {
    
    private int width;
    private int length;
    private int height;

    public Item(int width, int length, int height) throws DimensionLessThenOneException {
        if(width < 1 || length < 1 || height < 1){
            throw new DimensionLessThenOneException();
        }
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public int getVolume(){
        return width*length*height;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {\n\t\t" +
                "width = " + width +
                ", length = " + length +
                ", height = " + height +
                ", volume = " + getVolume() +
                "\n\t\t}";
    }

}
