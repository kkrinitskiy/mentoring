package data_bases.hw1.task_05;

import lombok.Builder;
import lombok.Data;

@Builder
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
