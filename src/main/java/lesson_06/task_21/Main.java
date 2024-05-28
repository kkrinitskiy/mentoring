package lesson_06.task_21;

import java.text.MessageFormat;

public class Main {

    public static void main(String[] args) {
        Student s1 = new Student("Kirill");
        s1.addMark(5);
        s1.addMark(4);
        s1.addMark(5);
        s1.addMark(4);
        s1.addMark(3);

        Student s2 = new Student("Oleg");
        s2.addMark(3);
        s2.addMark(3);
        s2.addMark(3);
        s2.addMark(2);
        s2.addMark(3);

        Student s3 = new Student("Petr");
        s3.addMark(4);
        s3.addMark(4);
        s3.addMark(4);
        s3.addMark(2);
        s3.addMark(4);

        Student s4 = new Student("Alisa");
        s4.addMark(3);
        s4.addMark(4);
        s4.addMark(3);
        s4.addMark(3);
        s4.addMark(4);

        Student s5 = new Student("Natalia");
        s5.addMark(5);
        s5.addMark(4);
        s5.addMark(5);
        s5.addMark(5);
        s5.addMark(5);

        Group group = new Group("GroupName");
        group.addStudent(s1);
        group.addStudent(s2);
        group.addStudent(s3);
        group.addStudent(s4);
        group.addStudent(s5);


        group.getStudents().forEach(System.out::println);
        System.out.println();
        group.getListStudentByAverage().stream().forEach(System.out::println);



    }
}
