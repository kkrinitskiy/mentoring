package multithreading.task_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Multithreading {

    public static void main(String[] args) {
        Random r = new Random();
        List<Thread> l = new ArrayList<>();

        try {
            for (int i = 1; i <= 5; i++) {
                Thread thread = new CounterThread("Поток №%d".formatted(i));
                TimeUnit.SECONDS.sleep(r.nextInt(1, 3));
                thread.start();
                l.add(thread);
            }

            for (Thread thread : l) {
                thread.join();
            }

            System.out.println(CounterThread.counter.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
