package lesson_05.task_04.models;

import java.util.ArrayList;
import java.util.Random;

import lesson_05.task_04.Treasure;

public class Island {

    private final String name;
    private final Chest<Treasure> chest;

    public Island(String name) {
        this.name = name;
        this.chest = generateTreasureChest();
    }

    private Chest<Treasure> generateTreasureChest(){
        int luck = new Random().nextInt(1, 100);
            if(luck <= 33){
                return new Chest<Treasure>(new Nothing(), this);
            } else if(luck <= 66){
                int quantity = new Random().nextInt(500, 1000);
                return new Chest<Treasure>(new Gold(quantity), this);
            } else {
                int quantity = new Random().nextInt(500, 1000);
                return new Chest<Treasure>(new Diamonds(quantity), this);
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

    public Chest<? extends Treasure> getChest() {
        return chest;
    }

}
