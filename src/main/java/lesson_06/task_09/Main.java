package lesson_06.task_09;

import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<String> ips = new TreeSet<>((a, b) -> {
            String[] c = a.split("\\.");
            String[] d = b.split("\\.");
            int comparator = Integer.compare(Integer.parseInt(c[3]), Integer.parseInt(d[3]));
            if(comparator == 0){
                comparator = Integer.compare(Integer.parseInt(c[2]), Integer.parseInt(d[2]));
            }
            if(comparator == 0){
                comparator = Integer.compare(Integer.parseInt(c[1]), Integer.parseInt(d[1]));
            }
            if(comparator == 0){
                comparator = Integer.compare(Integer.parseInt(c[0]), Integer.parseInt(d[0]));
            }


            return comparator;

        });

        ips.addAll(Set.of(
                "192.168.0.1", "192.168.0.22",
                "192.168.10.15", "255.255.0.255",
                "0.0.0.0", "192.168.1.22"));

        System.out.println(ips);
    }
}
