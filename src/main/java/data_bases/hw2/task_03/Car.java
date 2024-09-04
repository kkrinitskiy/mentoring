package data_bases.hw2.task_03;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "cars")
public class Car implements Transportable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String mark;
   private String model;
   @ManyToOne
   @JoinColumn(name = "body_type_id", referencedColumnName = "id")
   private TypesOfBodies bodyType;
   private Double width;
   private Double height;
   private Double length;

   @Override
   public double costDelivery() {
      return bodyType.getCostDelivery();
   }
}
