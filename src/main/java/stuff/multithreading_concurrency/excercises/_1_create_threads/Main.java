package stuff.multithreading_concurrency.excercises._1_create_threads;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }


    /**
     * Здесь демонстрируется работа потоков - потоки в системе выполняются одновременно
     * и в данном случае последовательность выполнения не гарантированна
     */
    private static void firstEx(){
        Thread t1 = new Thread(new RunnableImpl("First task"));
        Thread t2 = new Thread(new RunnableImpl("Second task"));
        Thread t3 = new Thread(new RunnableImpl("Third task"));
        Thread t4 = new Thread(new RunnableImpl("Fourth task"));
        Thread t5 = new Thread(new RunnableImpl("Fifth task"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static void secondEx(){
        Thread t1 = new Thread(() -> System.out.println(Arrays.toString(fibonacci(1))));
        Thread t2 = new Thread(() -> System.out.println(Arrays.toString(fibonacci(2))));
        Thread t3 = new Thread(() -> System.out.println(Arrays.toString(fibonacci(3))));
        Thread t4 = new Thread(() -> System.out.println(Arrays.toString(fibonacci(4))));
        Thread t5 = new Thread(() -> System.out.println(Arrays.toString(fibonacci(5))));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static int[] fibonacci(int n) {
        if(n == 1) { return new int[]{0}; }
        if(n == 2) { return new int[]{0, 1}; }
        int[] fibonacci = new int[n];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i = 2; i < n; i++) {
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        return fibonacci;
    }
}
