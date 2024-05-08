package lesson_04.hw4.transport_company_core;

import java.text.MessageFormat;

public class Container {
    private Transportable item;
    private double width;
    private double height;
    private double length;
    
    public Container(double width, double height, double length) {
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

    public boolean loadingItem(Transportable item){
        if(canTransport(item)){
        this.item = item;
        System.out.println(MessageFormat.format("погрузка {0} выполнена успешно", item.getClass().getSimpleName()));
        return true;
        }
        System.out.println(MessageFormat.format("погрузка {0} не удалась", item.getClass().getSimpleName()));
        return false;
    }
}
