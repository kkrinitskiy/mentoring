package stuff.multithreading_concurrency.priority;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            exec.execute(
                    new SimplePriorities(Thread.MIN_PRIORITY)
            );
        }
            exec.execute(
                    new SimplePriorities(Thread.MAX_PRIORITY)
            );

        exec.shutdown();


    }



}
