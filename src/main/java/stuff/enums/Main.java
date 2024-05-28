package stuff.enums;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println(Apple.FUJI == Orange.NAVEL);
        System.out.println(Planet.EARTH.surfaceWeight(82));


        double x = new Scanner(System.in).nextDouble();
        double y = new Scanner(System.in).nextDouble();

        for (Operation op : Operation.values()){
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }

        Month april = Month.APRIL;
    }



}
