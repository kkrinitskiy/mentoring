package data_bases.hw2.task_01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(Employee.builder().firstName("Добрыня").lastName("Колобродов").patronymic("Микулич").age(30).experience(8).salary(120000.0).build());
        employees.add(Employee.builder().firstName("Мирослав").lastName("Криворот").patronymic("Светозарич").age(28).experience(6).salary(110000.0).build());
        employees.add(Employee.builder().firstName("Забава").lastName("Кривоярова").patronymic("Ярополковна").age(26).experience(6).salary(110000.0).build());
        employees.add(Employee.builder().firstName("Потап").lastName("Воротынцев").patronymic("Твердославич").age(45).experience(12).salary(110000.0).build());
        employees.add(Employee.builder().firstName("Любава").lastName("Молчанова").patronymic("Добрыневна").age(49).experience(17).salary(120000.0).build());
        employees.add(Employee.builder().firstName("Елисей").lastName("Булгаков").patronymic("Лютомирович").age(31).experience(8).salary(120000.0).build());
        employees.add(Employee.builder().firstName("Ярополк").lastName("Тугарин").patronymic("Всеволодович").age(52).experience(10).salary(125000.0).build());
        employees.add(Employee.builder().firstName("Милолика").lastName("Златовласка").patronymic("Светозаровна").age(27).experience(10).salary(125000.0).build());
        employees.add(Employee.builder().firstName("Ратибор").lastName("Горыня").patronymic("Святославич").age(33).experience(11).salary(125000.0).build());
        employees.add(Employee.builder().firstName("Ладомира").lastName("Добрынская").patronymic("Мирославна").age(30).experience(8).salary(125000.0).build());

        EmployeeDAO.saveAll(employees);

        System.out.println("##### ВЫВОДИМ СПИСОК СОТРУДНИКОВ ######");
        List<Employee> employeesFromDB = EmployeeDAO.getAllEmployees();
        employeesFromDB.forEach(System.out::println);

        System.out.println("##### ИЩЕМ СОТРУДНИКА МЛАДШЕ 40 ЛЕТ И С НАИБОЛЬШИМ СТАЖЕМ ######");
        Employee employee = employeesFromDB.stream()
                .filter(e -> e.getAge() < 45)
                .max(Comparator.comparingInt(Employee::getExperience))
                .orElseThrow(() -> new RuntimeException("Сотрудник не нвйден"));

        System.out.println(employee);

        System.out.println("##### ПОВЫШАЕМ ЗП НА 20% И СОХРАНЯЕМ В БД ######");
        employee.setSalary(employee.getSalary() * 1.20);
        System.out.println(employee);
        EmployeeDAO.update(employee);


        System.out.println("##### ВЫВОДИМ СПИСОК СОТРУДНИКОВ ######");
        employeesFromDB = EmployeeDAO.getAllEmployees();
        employeesFromDB.forEach(System.out::println);

        System.out.println("##### ИЩЕМ САМОГО СТАРОГО СОТРУДНИКА И УВОЛЬНЯЕМ ######");
        Employee oldestOne = employeesFromDB.stream()
                .max(Comparator.comparingInt(Employee::getAge))
                .orElseThrow(() -> new RuntimeException("Сотрудник не нвйден"));
        EmployeeDAO.delete(oldestOne);

        System.out.println("##### ВЫВОДИМ СПИСОК СОТРУДНИКОВ ######");
        employeesFromDB = EmployeeDAO.getAllEmployees();
        employeesFromDB.forEach(System.out::println);

        System.out.println("##### СОЗДАЕМ НОВОГО СОТРУДНИКА И ДОБАВЛЯЕМ ######");
        Employee newEmployee = Employee.builder().firstName("Иван").lastName("Иванов").patronymic("Иванович").age(18).experience(17).salary(9000.0).build();
        EmployeeDAO.save(newEmployee);

        System.out.println("##### ВЫВОДИМ СПИСОК СОТРУДНИКОВ ######");
        employeesFromDB = EmployeeDAO.getAllEmployees();
        employeesFromDB.forEach(System.out::println);

        System.out.println("##### ВЫВОДИМ СПИСОК СОТРУДНИКОВ ######");
        System.out.println("##### ОТСОРТИРОВАННЫЙ С ПОМОЩЬЮ HQL ######");
        List<Employee> sortedBySalaryThenExperienceThenLastName = EmployeeDAO.getEmployeesSortedBySalaryThenByExpThenByLastName();
        sortedBySalaryThenExperienceThenLastName.forEach(System.out::println);

        System.out.println("##### ОТСОРТИРОВАННЫЙ С ПОМОЩЬЮ РЕАЛИЗАЦИИ КОМПАРАТОРА ######");
        employeesFromDB.sort((o1, o2) -> o1.getSalary() > o2.getSalary() ? 1 :
                o1.getSalary() < o2.getSalary() ? -1 :
                        o1.getExperience() > o2.getExperience() ? 1 :
                                o1.getExperience() < o2.getExperience() ? -1 :
                                        o1.getLastName().compareTo(o2.getLastName()));
        employeesFromDB.forEach(System.out::println);

    }
}
