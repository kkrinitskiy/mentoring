package stuff.hibernate.assotiations.unidirectional.m2m;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private Date deadline;

}
