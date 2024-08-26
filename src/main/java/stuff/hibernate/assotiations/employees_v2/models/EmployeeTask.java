package stuff.hibernate.assotiations.employees_v2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "task_v2")
public class EmployeeTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Date deadline;

    @ManyToMany(mappedBy = "tasks")
    private Set<Employee> employees =  new HashSet<>();

    // Переопределяем hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, description, deadline);
    }

    // Переопределяем equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmployeeTask task = (EmployeeTask) obj;
        return Objects.equals(id, task.id);
    }
}
