package data_bases.hw1.task_05;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
class Cat {
    private int id;
    private String name;
    private int age;
    private String breed;
    private String color;
}
