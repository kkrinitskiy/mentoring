package stuff.multithreading_concurrency.excercises._2_create_executors;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int n = 5;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(n);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService workStealingPool = Executors.newWorkStealingPool();

        giveExecutorToMethods(workStealingPool, 5);

    }

    private static void giveExecutorToMethods(ExecutorService executorService, int n) {
        firstEx(executorService, n);
        secondEx(executorService, n);

    }


    private static void firstEx(ExecutorService service, int n){

        for (int i = 0; i < n; i++){
            service.execute(new RunnableImpl("Task " + i));
        }


    }

    private static void secondEx(ExecutorService service, int n){

        for (int i = 1; i <= n; i++){
            int finalI = i;
            service.execute(() -> System.out.println(Arrays.toString(fibonacci(finalI))));
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
