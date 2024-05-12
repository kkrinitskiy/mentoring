package lesson_05.task_04.models;

import java.util.ArrayList;
import java.util.Random;

import lesson_05.task_04.Treasure;

public class Island {

    private final String name;
    private final Treasure treasure;

    public Island(String name) {
        this.name = name;

        int luck = new Random().nextInt(1, 100);
            if(luck <= 33){
                this.treasure = new Nothing();
            } else if(luck <= 66){
                int quantity = new Random().nextInt(500, 1000);
                this.treasure = new Gold(quantity);
            } else {
                int quantity = new Random().nextInt(500, 1000);
                this.treasure = new Diamonds(quantity);
            }
    }

    public static ArrayList<Island> islandFactory(int count){
        ArrayList<Island> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add(new Island("Island №" + i));
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public Treasure getTreasure() {
        return treasure;
    }

}
