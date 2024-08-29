package stuff.hibernate.assotiations.bidirectional.m2m;

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
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
class EmployeeTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String description;
    @ManyToMany(mappedBy = "tasks")
    private List<Employee> employees;
    private Date deadline;

}
