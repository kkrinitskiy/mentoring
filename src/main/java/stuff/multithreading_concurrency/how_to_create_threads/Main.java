package stuff.multithreading_concurrency.how_to_create_threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        threads.add(new ExtendingThread());
        threads.add(new Thread(new RunnableTask()));
        threads.add(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running thread with anonymous class");
            }
        }));
        threads.add(new Thread(() -> System.out.println("Running thread with lambda")));

        try (ExecutorService executor = Executors.newFixedThreadPool(threads.size() + 1)) {
            threads.forEach(executor::execute);
            executor.submit(() -> System.out.println("Running thread created in executor"));

            Future<String> future = executor.submit(new CallableTask());
            System.out.println(future.get());

            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
                System.out.println("Run async thread with completableFuture");
            });

            System.out.println(completableFuture.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Run async thread with completableFuture");
        });



    }
}
