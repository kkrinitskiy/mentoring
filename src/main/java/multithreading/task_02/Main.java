package multithreading.task_02;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static volatile AtomicInteger NUMB = new AtomicInteger(5000);
    private final static int FINISH_TOP = 10000;
    private final static int FINISH_BOTTOM = 0;
    private static volatile AtomicBoolean hasWinner = new AtomicBoolean(false);

    public static void main(String[] args) {


        Thread thread_01 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!hasWinner.get()){
                    if(NUMB.get() >= FINISH_TOP) {
                        hasWinner.set(true);
                        System.out.println("+ тред победил! " + NUMB.get());
                        return;
                    }
                    NUMB.incrementAndGet();
                    System.out.println("+ тред " + NUMB.get());
                }
                    System.out.println("+ тред завершил гонку " + NUMB.get());

            }
        });

        Thread thread_02 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!hasWinner.get()){
                    if(NUMB.get() <= FINISH_BOTTOM) {
                        hasWinner.set(true);
                        System.out.println("- тред победил! " + NUMB.get());
                        return;
                    }
                    NUMB.decrementAndGet();
                    System.out.println("- тред " + NUMB.get());
                }
                    System.out.println("- тред завершил гонку " + NUMB.get());

            }
        });

        thread_01.start();
        thread_02.start();

        try {
            thread_01.join();
            thread_02.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
