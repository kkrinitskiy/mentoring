package lesson_04.hw4;

import java.text.MessageFormat;

public class Conatiner {
    private Transportable item;
    private double width;
    private double height;
    private double length;
    
    public Conatiner(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public boolean canTransport(Transportable item){
        if( item.getHight() <= height &&
            item.getWidth() <= width &&
            item.getLength() <= length
            ){
                return true;
            }
            return false;
    }

    public void loadingItem(Transportable item){
        if(canTransport(item)){
        this.item = item;
        System.out.println(MessageFormat.format("погрузка {0} выполнена успешно", item.getClass().getSimpleName()));
        }
        System.out.println(MessageFormat.format("погрузка {0} не удалась", item.getClass().getSimpleName()));
    }
}
