package lesson_05.task_02;

import java.text.MessageFormat;

public abstract class Animal {
    private final String name;
    private final int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    

    @Override
    public String toString() {
        return MessageFormat.format("[name: {0}, age: {1}]", name, age);
    }
}
