package stuff.hibernate.assotiations.unidirectional.m2m;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
                .buildSessionFactory();

        createDataLists();

        Employee e1 = employees.get(0);
        Employee e2 = employees.get(1);
        EmployeeTask t1 = employeeTasks.get(0);
        EmployeeTask t2 = employeeTasks.get(1);

        e1.getTasks().add(t1);
        e2.getTasks().add(t2);

        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(e1);
            session.persist(e2);
            transaction.commit();
        }

        List<Employee> es;

        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Employee> fromEmployee = session.createQuery("from Employee", Employee.class);
            es = new ArrayList<>(fromEmployee.list());
            transaction.commit();
        }

        es.forEach(System.out::println);
    }

    private static void createDataLists(){
        employees.add(Employee.builder()
                .name("Alice Smith")
                .occupation("Software Engineer")
                .salary(85000)
                .age(28)
                .join(new Date())
                .tasks(new ArrayList<>())
                .build());

        employees.add(Employee.builder()
                .tasks(new ArrayList<>())
                .name("Bob Johnson")
                .occupation("Project Manager")
                .salary(95000)
                .age(35)
                .join(new Date())
                .build());

        employees.add(Employee.builder()
                .name("Charlie Brown")
                .tasks(new ArrayList<>())
                .occupation("UI/UX Designer")
                .salary(70000)
                .age(27)
                .join(new Date())
                .build());

        employees.add(Employee.builder()
                .name("Diana Prince")
                .occupation("Data Scientist")
                .salary(98000)
                .age(30)
                .tasks(new ArrayList<>())
                .join(new Date())
                .build());

        employees.add(Employee.builder()
                .name("Ethan Hunt")
                .occupation("DevOps Engineer")
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
