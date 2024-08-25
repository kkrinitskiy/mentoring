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
@Table(name = "employee_v2")
public class Employee {

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
    @JoinTable(name = "employee_task",
                joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<EmployeeTask> tasks = new HashSet<>();

    // Переопределяем hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, name, occupation, salary, age);
    }

    // Переопределяем equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(id, employee.id);
    }
}
