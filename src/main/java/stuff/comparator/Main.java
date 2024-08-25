package stuff.comparator;

import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> a = List.of("a1","a2","a3","a4","a5","a6");

        boolean a7 = a.stream().anyMatch(b -> b.equals("a7"));
        Optional<String> a71 = a.stream().filter(b -> b.equals("a7")).findAny();
        System.out.println(a71.orElse("asdasd"));

    }
}
