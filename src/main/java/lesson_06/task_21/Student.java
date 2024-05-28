package lesson_06.task_21;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student {
    private String name;
    private List<Integer> marks;

    public Student(String name) {
        this.name = name;
        marks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void addMark(int mark){
        if(mark <= 5 && mark >= 1) {
            marks.add(mark);
            marks.sort(Comparator.naturalOrder());
        }
    }

    public double getAverageMark(){
        if(!marks.isEmpty()) {
            return marks.stream().mapToInt(Integer::intValue).average().getAsDouble();
        }else {
            throw new RuntimeException("mark list is empty!");
        }
    }

    @Override
    public String toString() {
        return  MessageFormat.format("Ф.И.О.: {0}; Средний балл: {1}",
                getName(), getAverageMark());
    }
}
