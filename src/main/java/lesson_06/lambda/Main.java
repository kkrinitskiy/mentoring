package lesson_06.lambda;


import java.util.Random;

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


        NumericTest2 isDivisor = (a, b) -> (a % b) == 0;
        System.out.println(isDivisor.test(5,12));

        AppleTest isApple = a -> {
            switch (a.toLowerCase()){
                case "apple" -> {
                    return true;
                }
                case null, default -> {
                    return false;
                }
            }
        };

        System.out.println(isApple.blockedAppleTest("banana"));
        System.out.println(isApple.blockedAppleTest("apple"));


//        Factorial factorial = a -> {
//            if(a <= 0){
//                return 0;
//            }
//            int result = 1;
//            for (int i = 1; i <= a; i++) {
//                result = i * result;
//            }
//            return result;
//        };
//
//
//        System.out.println(factorial.get(10));
//
//        StringFunc reverse = str -> {
//            if(str.isBlank() || str == null){
//                return "";
//            }
//            String result = "";
//            for (int i = str.length()-1; i >= 0; i--) {
//                result = result + str.charAt(i);
//            }
//            return result;
//        };
//
//        System.out.println(reverse.func("Lambda"));

        Function<Integer> factorial = num -> {
          if(num <= 0){
              return 0;
          }
          int result = 1;

            for (int i = 1; i <= num; i++) {
                result = result * i;
            }
            return result;
        };

        printResultOfLambda(factorial, 5);

        printResultOfLambda(str -> {
            if(str.isBlank() || str == null){
                return "";
            }

            String result = "";

            for (int i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }
            return result;
        }, "Generics");


        double[] doubles = new double[]{1,2,3,4,5,6,7,8,9,10};

        DoubleNumericArrayFunc getAverage = a -> {
            if(a.length == 0){
                throw new EmptyArrayException();
            }

            int sum = 0;

            for (int i = 0; i < a.length; i++) {
                sum += i;
            }

            return sum/ a.length;
        };

        try {
            System.out.println(getAverage.func(doubles));
        }catch (EmptyArrayException e){
            e.printStackTrace();
        }


        int num = 5; // должна быть фактический финальной

        Function<Integer> func = e -> {
//            num++;   ошибка
            return e + num;
        };

//       num++;   ошибка

        System.out.println(stringOp(Main::reverse, "в метод была передана ссылка на статический метод класса Main"));

        MyStringOps mso = new MyStringOps();

        System.out.println(stringOp(mso::reverse, "в метод была переданна ссылка на метод объекта mso"));


        HighTemp[] weekDayHighs = {
                new HighTemp(22), new HighTemp(23),
                new HighTemp(28), new HighTemp(25),
                new HighTemp(33), new HighTemp(26),
                new HighTemp(31)
        };

        HighTemp highTemp = new HighTemp(28);

        System.out.println(counter(weekDayHighs, HighTemp::lessThanTemp, highTemp));


    }

    public static <T>void printResultOfLambda(Function<T> func, T param){
        System.out.println(func.function(param));
    }

    public static String stringOp(StringFunc sf, String str){
        return sf.func(str);
    }


    public static String reverse(String str){
        if(str.isBlank() || str == null){
            return "";
        }

        String result = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }

    public static <T> int counter(T[] vals, MyFunc<T> f, T v){
        int count = 0;

        for (int i = 0; i < vals.length; i++) {
            if(f.func(vals[i], v)){
                count++;
            }
        }

        return count;
    }

    MyConstr mc = HighTemp::new;

    HighTemp highTemp = mc.func(30);


}
