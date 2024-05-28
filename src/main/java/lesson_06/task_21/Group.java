package lesson_06.task_21;

import java.util.*;

public class Group {
    private String name;
    private Set<Student> students;

    public Group(String name){
        this.name = name;
        students = new TreeSet<>(Comparator.comparing(Student::getName));
    }

    public void addMark(Student student, int mark){
        if(students.contains(student)){
            students.forEach(s -> {
                if(s.equals(student)){
                    student.addMark(mark);
                }
            });
        }
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public String getName() {
        return name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Student> getListStudentByAverage(){
        Set<Student> r = new TreeSet<>(Comparator.comparing(Student::getAverageMark));
        r.addAll(students);
        return r;
    }
}
