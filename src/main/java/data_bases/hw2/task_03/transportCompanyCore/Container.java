package data_bases.hw2.task_03.transportCompanyCore;

import data_bases.hw2.task_03.entities.Transportable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Container {
    private Double width;
    private Double height;
    private Double length;
    @Getter
    @Setter
    private Transportable item;

    public Container(Double width, Double height, Double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }



    private boolean canTransport(Transportable item) {
        return this.width >= item.getWidth() && this.height >= item.getHeight() && this.length >= item.getLength();
    }
}

