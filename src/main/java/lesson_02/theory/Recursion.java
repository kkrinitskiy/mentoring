package lesson_02.theory;

import java.util.HashMap;
import java.util.Map;

public class Recursion {
//  Рекурия - метод решения задачи, при котором функция вызывает сама себя, пока не достигнет базового случая.
//  Базовый случай (базис рекурсии) - условие для выхода из рекурсии.
//  Рекурсивный случвй (шаг рекурсии) - вызов функцией самой себя при измененных параметрах
//
//

    private static final Map<Integer, Integer> cache = new HashMap<>();


    public static void main(String[] args) {


        System.out.println(getFactorial(10));

        int[] nums = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90};
        System.out.println(recursiveArraySum(nums, nums.length - 1));

        System.out.println(recursiveExponentiation(5, 5));

//        System.out.println(recursiveFibonacci(10));

        System.out.println(recursiveFibonacciWithMemory(100));

        System.out.println(greatestCommonDivisor(10, 5));


    }





    public static int getFactorial(int num){
        if(num <= 1){
            return num;
        } else {
            return num * getFactorial(num - 1);
        }

    }

    public static int recursiveArraySum(int[] nums, int i){
        if(i < 1){
            return nums[i];
        } else {
            return nums[i] + recursiveArraySum(nums, i - 1);
        }
    }

    public static int recursiveExponentiation(int num, int deg){
        if(deg <= 1){
            return 1;
        } else {
            return num * recursiveExponentiation(num, deg - 1);
        }
    }

    public static int recursiveFibonacci(int num){
        if(num <= 1){
            return num;
        } else {
            return recursiveFibonacci(num - 1) + recursiveFibonacci(num - 2);
        }
    }

    public static int recursiveFibonacciWithMemory(int n) {
        if (n <= 1) {
            return n;
        }

        if (!cache.containsKey(n)) {
            int result = recursiveFibonacciWithMemory(n - 1) + recursiveFibonacciWithMemory(n - 2);
            cache.put(n, result);
        }

        return cache.get(n);
    }

    public static int greatestCommonDivisor(int a, int b){
        if(b == 0){
            return a;
        }
        if(a == 0){
            return b;
        }
        if(a > b){
            return greatestCommonDivisor(a % b, b);
        } else {
            return greatestCommonDivisor(a, b % a);
        }
    }
}


