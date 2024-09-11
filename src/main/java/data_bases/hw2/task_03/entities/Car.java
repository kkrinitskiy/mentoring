package data_bases.hw2.task_03.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "cars")
public class Car implements Transportable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String mark;
   private String model;
   @ManyToOne
   @JoinColumn(name = "body_type_id", referencedColumnName = "id")
   private TypesOfBodies bodyType;
   private Integer width;
   private Integer height;
   private Integer length;

   @Override
   public Double costDelivery() {
      return bodyType.getCostDelivery();
   }
}
