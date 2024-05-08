package lesson_04.hw4;

public interface Transportable {

    double costDelivery();
    double getWidth();
    double getHight();
    double getLength();
    
    default double getVolume(){
        return getHight()*getLength()*getWidth();
    };
    
}
