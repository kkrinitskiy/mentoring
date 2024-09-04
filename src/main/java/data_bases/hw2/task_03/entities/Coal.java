package data_bases.hw2.task_03.entities;

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
@Table(name = "coal")
public class Coal implements Transportable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double width;
    private Double height;
    private Double length;
    private Double costDelivery;

    @Override
    public Double costDelivery() {
        return costDelivery;
    }
}
