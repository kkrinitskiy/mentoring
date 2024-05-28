package lesson_06.task_01;

public class Main {
    public static void main(String[] args) {
        Max3<Integer> refInt;

        refInt = (a, b, c) -> {
            if (a > b) {
                return a > c ? a : c;
            }else {
                return b > c ? b : c;
            }
        };

        int res = refInt.max(5, 7, 3);
        System.out.println("res = "+res);

        Max3<Double> refDouble;

        refDouble = (a, b, c) -> {
            if (a > b) {
                return a > c ? a : c;
            }else {
                return b > c ? b : c;
            }
        };

        double resMax = refDouble.max(3.88, 2.55, 4.11);
        System.out.println("resMax = "+resMax);

    }
}
