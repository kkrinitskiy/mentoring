package lesson_06.lambda;

import java.security.PrivilegedAction;
import java.util.function.Function;
import java.util.function.IntSupplier;

public class Main {
    public static void main(String[] args) {

        MyNumber myNum;

        myNum = () -> 11;

        System.out.println(myNum.getNumber());
        myNum = () -> Math.random() * 100;
        System.out.println(myNum.getNumber());

        NumericTest isEven = (n) -> n % 2 == 0;

        System.out.println(isEven.test(10));
        System.out.println(isEven.test(9));

        NumericTest isNegative = (n) -> n < 0;

        System.out.println(isNegative.test(10));
        System.out.println(isNegative.test(-10));




    }


}
