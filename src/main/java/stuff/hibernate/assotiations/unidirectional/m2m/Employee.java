package stuff.hibernate.assotiations.unidirectional.m2m;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String occupation;
    private Integer salary;
    private Integer age;
    @Column(name = "join_date")
    private Date join;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<EmployeeTask> tasks;


}
