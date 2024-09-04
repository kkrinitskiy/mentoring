package data_bases.hw2.task_03;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "wood")
public class Wood implements Transportable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double width;
    private Double height;
    private Double length;
    private Double costDelivery;

    @Override
    public double costDelivery() {
        return costDelivery;
    }


}
