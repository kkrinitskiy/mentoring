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
    private Integer width;
    private Integer height;
    private Integer length;

    @Override
    public Double costDelivery() {
        return getVolume().doubleValue() * 50;
    }
}
