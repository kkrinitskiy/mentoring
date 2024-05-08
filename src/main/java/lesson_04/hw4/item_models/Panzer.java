package lesson_04.hw4.item_models;

import lesson_04.hw4.transport_company_core.Transportable;

public class Panzer implements Transportable {

    private final double width;
    private final double height;
    private final double length;
    private final double costPerOnePointVolume;

    public Panzer(double width, double height, double length, double costPerOnePointVolume) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.costPerOnePointVolume = costPerOnePointVolume;
    }
    
    @Override
    public double costDelivery() {
        return costPerOnePointVolume*getVolume()/100_000;
    }

    @Override
    public double getWidth() {
       return width;
    }

    @Override
    public double getHight() {
        return height;
    }

    @Override
    public double getLength() {
       return length;
    }
    
}
