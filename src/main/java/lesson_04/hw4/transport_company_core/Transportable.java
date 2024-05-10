package lesson_04.hw4.transport_company_core;

public interface Transportable {

    double costDelivery();
    double getWidth();
    double getHight();
    double getLength();

    default double getVolume(){
        return getHight()*getLength()*getWidth();
    };

    
    
}
