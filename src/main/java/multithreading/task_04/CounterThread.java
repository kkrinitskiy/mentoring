package multithreading.task_04;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CounterThread extends Thread{

    public static volatile AtomicInteger counter = new AtomicInteger(0);
    public static Random r = new Random();

    public CounterThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (counter.get() < 42){
            try{
                    TimeUnit.SECONDS.sleep(r.nextLong(1, 3));
                    if (counter.get() < 42) {
                        System.out.println(Thread.currentThread().getName() + " " + counter.incrementAndGet());
                    } else {
                        return;
                    }

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
