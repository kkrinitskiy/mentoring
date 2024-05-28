package lesson_04.item_models;

import java.text.MessageFormat;

import lesson_04.transport_company_core.Transportable;

public class Elephant implements Transportable {

    private final double width;
    private final double height;
    private final double length;
    private final double costPerOnePointVolume;

    public Elephant(double width, double height, double length, double costPerOnePointVolume) {
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

      @Override
    public String toString() {
        return  MessageFormat.format(
            "\n{0}, с габаритами: {1}x{2}x{3}, доставка стоит: {4}", 
            getClass().getSimpleName(), 
            getWidth(), 
            getHight(), 
            getLength(),
            costDelivery());
    }
    
}
