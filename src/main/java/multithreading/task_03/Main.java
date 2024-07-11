package multithreading.task_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] array = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };

        int n = new Scanner(System.in).nextInt();
        findNumInArray(n, array);
    }

    public static void findNumInArray(int n, int[][] arr){
        List<Thread> bloodhounds = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            int fin = i;
            bloodhounds.add(new Thread(() -> {
                for (int j = 0; j < arr[fin].length; j++) {
                    if(arr[fin][j] == n){
                        System.out.printf("Элемент %d найден в ячейке [%d][%d]%n", n, fin, j);
                        return;
                    }
                }
                System.out.printf("В строке %d нет элемента, равного %d\n", fin, n);
            }));
        }
        bloodhounds.forEach(Thread::start);
    }
}
