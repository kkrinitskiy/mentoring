package data_bases.hw2.task_03;

interface Transportable {
    double costDelivery();
    double getWidth();
    double getHeight();
    double getLength();
    default double getVolume(){
        return getWidth() * getHeight() * getLength();
    }
}
