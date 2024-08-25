package stuff.multithreading_concurrency.runners;

import java.util.ArrayList;
import java.util.List;

public class RaceSimulation {
    public static void main(String[] args) {
        List<Thread> runners = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            Thread runner = new Thread(new Runner("Бегун " + i));
            runners.add(runner);
            runner.start();
        }

        for (Thread runner : runners) {
            try {
                runner.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Гонка завершена");

    }
}
