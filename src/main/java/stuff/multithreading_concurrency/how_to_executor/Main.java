package stuff.multithreading_concurrency.how_to_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            int num = i;
            executor.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println("Thread: " + num + " " + j);
                }
            });
        }
        executor.shutdown();

    }
}
