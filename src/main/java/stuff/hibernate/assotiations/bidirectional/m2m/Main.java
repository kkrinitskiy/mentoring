package stuff.hibernate.assotiations.bidirectional.m2m;

import com.github.dockerjava.api.model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private static List<Employee> employees = new ArrayList<>();
    private static List<EmployeeTask> employeeTasks = new ArrayList<>();
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(EmployeeTask.class)
                .addAnnotatedClass(Occupation.class)
                .buildSessionFactory();

        createDataLists();
        Occupation o1 = new Occupation();
        o1.setOccupationName("director");
        Employee e1 = employees.get(0);
//        Employee e2 = employees.get(1);
        EmployeeTask t1 = employeeTasks.get(0);
        e1.setOccupation(o1);
//        EmployeeTask t2 = employeeTasks.get(1);

        e1.getTasks().add(t1);
//        e2.getTasks().add(t2);

        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(o1);
            e1.setOccupation(o1);
            session.persist(e1);
            transaction.commit();
        }

    }

    private static void createDataLists(){
        employees.add(Employee.builder()
                .name("Alice Smith")
                .salary(85000)
                .age(28)
                .join(new Date())
                .tasks(new ArrayList<>())
                .build());

        employees.add(Employee.builder()
                .tasks(new ArrayList<>())
                .name("Bob Johnson")
                .salary(95000)
                .age(35)
                .join(new Date())
                .build());

        employees.add(Employee.builder()
                .name("Charlie Brown")
                .tasks(new ArrayList<>())
                .salary(70000)
                .age(27)
                .join(new Date())
                .build());

        employees.add(Employee.builder()
                .name("Diana Prince")
                .salary(98000)
                .age(30)
                .tasks(new ArrayList<>())
                .join(new Date())
                .build());

        employees.add(Employee.builder()
                .name("Ethan Hunt")
                .salary(90000)
                .tasks(new ArrayList<>())
                .age(32)
                .join(new Date())
                .build());


        employeeTasks.add(EmployeeTask.builder()
                .description("Implement login feature")
                .deadline(new Date(System.currentTimeMillis() + 86400000))
                .build());

        employeeTasks.add(EmployeeTask.builder()
                .description("Prepare project presentation")
                .deadline(new Date(System.currentTimeMillis() + 604800000))
                .build());

        employeeTasks.add(EmployeeTask.builder()
                .description("Design homepage layout")
                .deadline(new Date(System.currentTimeMillis() + 172800000))
                .build());

        employeeTasks.add(EmployeeTask.builder()
                .description("Analyze sales data")
                .deadline(new Date(System.currentTimeMillis() + 259200000))
                .build());

        employeeTasks.add(EmployeeTask.builder()
                .description("Set up CI/CD pipeline")
                .deadline(new Date(System.currentTimeMillis() + 864000000))
                .build());
    }
}
