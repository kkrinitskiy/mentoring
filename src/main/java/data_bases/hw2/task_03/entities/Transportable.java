package data_bases.hw2.task_03.entities;

public interface Transportable {
    Double costDelivery();
    Integer getWidth();
    Integer getHeight();
    Integer getLength();
    default Integer getVolume(){
        return getWidth() * getHeight() * getLength();
    }
}
