package lesson_04.hw4;

import java.util.Random;


public class HWUtils {
    public static Random r = new Random();

    public static double getRandomWidth(){
        return r.nextDouble(200, 300);
    }

    public static double getRandomHight(){
        return r.nextDouble(300, 400);
    }

    public static double getRandomLength(){
        return r.nextDouble(3000, 6000);
    }
}
