package stuff.multithreading_concurrency.quit_infinity_run;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        Thread t = new Thread(() -> {
            int counter = 0;
            while (!input.equals("quit")) {
                System.out.println(++counter);
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        t.start();
    }
}
