package lesson_05.task_04.models;

import lesson_05.task_04.Treasure;
import java.text.MessageFormat;


public class Chest <T extends Treasure>{
    private T treasure;
    private Island island;

    public Chest(T treasure, Island island) {
        this.treasure = treasure;
        this.island = island;
    }

    public Class<? extends Treasure> checkChest(){
        return treasure.getClass();
    }

    public T takeTreasure(){
        T takenAway = treasure;
        treasure = (T) new Nothing();
        return takenAway;
    }

    @Override
    public String toString() {
        if(!treasure.getClass().equals(Nothing.class)){
            return MessageFormat.format("На острове \"{0}\" найден сундук, " +
            "содержащий в себе {1}, в количестве {2}, на общую стоимость в {3}",
            island.getName(),
            treasure.getClass().getSimpleName(),
            treasure.getQuantity(),
            treasure.getPrice());
        }else{
            return MessageFormat.format("На острове \"{0}\" найден пустой сундук. Штош, в другой раз повезет, пират!", island.getName());
        }
    }
    
}
