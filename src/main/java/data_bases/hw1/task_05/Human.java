package data_bases.hw1.task_05;

import lombok.Data;

@Data
class Human {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private Cat cat;
    private Dog dog;
}
