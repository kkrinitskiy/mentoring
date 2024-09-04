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
@Table(name = "types_of_bodies")
public class TypesOfBodies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type_of_body")
    private String type;
    @Column(name = "cost_delivery")
    private Double costDelivery;
}
