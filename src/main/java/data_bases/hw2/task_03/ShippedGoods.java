package data_bases.hw2.task_03;

import jakarta.persistence.*;

@Entity
@Table(name = "shipped_goods")
public class ShippedGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
