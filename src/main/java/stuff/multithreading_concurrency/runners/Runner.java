package stuff.multithreading_concurrency.runners;

import java.util.Random;

public class Runner implements Runnable{

    private final String name;
    private final int speed = new Random().nextInt(0, 10) + 1;
    private int distance = 0;
    private int finishDistance = 100;
    private static boolean hasWinner = false;

    public Runner(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (distance < finishDistance) {
            distance += speed;
            System.out.println(name + " пробежал " + distance + " метров");

            if(distance >= finishDistance && !hasWinner) {
                hasWinner = true;
                System.out.println(name + " пришел первым!");
            }

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
