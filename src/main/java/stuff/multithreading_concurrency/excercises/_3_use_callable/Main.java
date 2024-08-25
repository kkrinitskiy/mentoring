package stuff.multithreading_concurrency.excercises._3_use_callable;

import stuff.multithreading_concurrency.excercises._2_create_executors.RunnableImpl;

import java.util.*;
import java.util.concurrent.*;

public class Main {
    private static Random random = new Random();
    public static void main(String[] args) {

        secondEx();

    }

    private static void secondEx(){
        System.out.println("Введите количество задач с ожиданием:");

        int count = new Scanner(System.in).nextInt();

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i <= count; i++) {
            int n = i;
            service.execute(() -> {
                int freezeTime = random.nextInt(1, 11);
                try {
                    Thread.sleep(freezeTime* 1000L);
                    System.out.printf("Время ожидания задачи %d составило %d%n", n, freezeTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        service.shutdown();

    }

    private static void firstEx(){
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<Integer>> r = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            int n = i;
            r.add(service.submit(() -> {
                int sum = 0;
                for (int j : fibonacci(n)) {
                    sum += j;
                }
                return sum;
            }));
        }

        for (Future<Integer> future : r) {
            try{
                System.out.println(future.get());
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        service.shutdown();

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
