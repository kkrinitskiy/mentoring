package stuff.hibernate.employees;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import stuff.hibernate.employees.models.Employee;
import stuff.hibernate.employees.models.EmployeeTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(EmployeeTask.class)
                .buildSessionFactory();

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(null, "Alice Smith", "Software Engineer", 85000, 28, new Date()));
        employees.add(new Employee(null, "Bob Johnson", "Project Manager", 95000, 35, new Date()));
        employees.add(new Employee(null, "Charlie Brown", "UI/UX Designer", 70000, 27, new Date()));
        employees.add(new Employee(null, "Diana Prince", "Data Scientist", 98000, 30, new Date()));
        employees.add(new Employee(null, "Ethan Hunt", "DevOps Engineer", 90000, 32, new Date()));

        List<EmployeeTask> tasks = new ArrayList<>();
        tasks.add(new EmployeeTask(null, "Implement login feature", employees.get(0), new Date(System.currentTimeMillis() + 86400000)));
        tasks.add(new EmployeeTask(null, "Prepare project presentation", employees.get(1), new Date(System.currentTimeMillis() + 604800000)));
        tasks.add(new EmployeeTask(null, "Design homepage layout", employees.get(2), new Date(System.currentTimeMillis() + 172800000)));
        tasks.add(new EmployeeTask(null, "Analyze sales data", employees.get(3), new Date(System.currentTimeMillis() + 259200000)));
        tasks.add(new EmployeeTask(null, "Set up CI/CD pipeline", employees.get(4), new Date(System.currentTimeMillis() + 864000000)));

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            for(Employee employee : employees){
                session.persist(employee);
            }
            transaction.commit();
        }

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            for (EmployeeTask task : tasks) {
                session.persist(task);
            }

            transaction.commit();
        }

        List<Employee> employeeListFromDb = new ArrayList<>();
        List<EmployeeTask> tasksListFromDb = new ArrayList<>();

        try(Session session = sessionFactory.openSession()){
            employeeListFromDb = session.createQuery("from Employee", Employee.class).list();
            tasksListFromDb = session.createQuery("from EmployeeTask", EmployeeTask.class).list();
        }

        employeeListFromDb.forEach(System.out::println);
        tasksListFromDb.forEach(System.out::println);

    }
}
