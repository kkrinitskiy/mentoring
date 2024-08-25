package stuff.hibernate.assotiations.employees_v2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import stuff.hibernate.assotiations.employees_v2.models.Employee;
import stuff.hibernate.assotiations.employees_v2.models.EmployeeTask;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(EmployeeTask.class)
                .buildSessionFactory();

        List<Employee> employees = new ArrayList<>(Arrays.asList(
                Employee.builder()
                        .name("Alice Smith")
                        .occupation("Software Engineer")
                        .salary(85000)
                        .age(28)
                        .join(new Date())
                        .tasks(new HashSet<>())
                        .build(),
                Employee.builder()
                        .name("Bob Johnson")
                        .occupation("Project Manager")
                        .salary(95000)
                        .age(35)
                        .join(new Date())
                        .tasks(new HashSet<>())
                        .build(),
                Employee.builder()
                        .name("Charlie Brown")
                        .occupation("UI/UX Designer")
                        .salary(70000)
                        .age(27)
                        .join(new Date())
                        .tasks(new HashSet<>())
                        .build(),
                Employee.builder()
                        .name("Diana Prince")
                        .occupation("Data Scientist")
                        .salary(98000)
                        .age(30)
                        .join(new Date())
                        .tasks(new HashSet<>())
                        .build(),
                Employee.builder()
                        .name("Ethan Hunt")
                        .occupation("DevOps Engineer")
                        .salary(90000)
                        .age(32)
                        .join(new Date())
                        .tasks(new HashSet<>())
                        .build()
        ));

        // Список задач
        List<EmployeeTask> tasks = new ArrayList<>(Arrays.asList(
                EmployeeTask.builder()
                        .description("Implement login feature")
                        .deadline(new Date(System.currentTimeMillis() + 86400000))
                        .employees(new HashSet<>(Arrays.asList(employees.get(0))))
                        .build(),
                EmployeeTask.builder()
                        .description("Prepare project presentation")
                        .deadline(new Date(System.currentTimeMillis() + 604800000))
                        .employees(new HashSet<>(Arrays.asList(employees.get(1))))
                        .build(),
                EmployeeTask.builder()
                        .description("Design homepage layout")
                        .deadline(new Date(System.currentTimeMillis() + 172800000))
                        .employees(new HashSet<>(Arrays.asList(employees.get(2))))
                        .build(),
                EmployeeTask.builder()
                        .description("Analyze sales data")
                        .deadline(new Date(System.currentTimeMillis() + 259200000))
                        .employees(new HashSet<>(Arrays.asList(employees.get(3))))
                        .build(),
                EmployeeTask.builder()
                        .description("Set up CI/CD pipeline")
                        .deadline(new Date(System.currentTimeMillis() + 864000000))
                        .employees(new HashSet<>(Arrays.asList(employees.get(4))))
                        .build()
        ));

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            for (EmployeeTask task : tasks) {
                session.persist(task);
            }
            transaction.commit();
        }

        List<EmployeeTask> tasksListFromDb = new ArrayList<>();
        List<Employee> employeeListFromDb;

        try (Session session = sessionFactory.openSession()) {
            for (EmployeeTask task : tasks) {
                session.load(task, Integer.class);
            }
        }

        try (Session session = sessionFactory.openSession()) {

            for (int i = 0; i < employees.size(); i++) {
                HashSet<EmployeeTask> tasksSet = new HashSet<>();
                tasksSet.add(tasksListFromDb.get(i));
                employees.get(i).setTasks(tasksSet);
                session.merge(employees.get(i));
            }

        }


        try (Session session = sessionFactory.openSession()) {
            employeeListFromDb = session.createQuery("from Employee", Employee.class).list();
        }


// Вывод результатов
        employeeListFromDb.forEach(System.out::println);
        tasksListFromDb.forEach(System.out::println);

    }
}
