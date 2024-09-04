package data_bases.hw2.task_03.entities;

public interface Transportable {
    Double costDelivery();
    Double getWidth();
    Double getHeight();
    Double getLength();
    default Double getVolume(){
        return getWidth() * getHeight() * getLength();
    }
}
