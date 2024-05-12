package lesson_05.task_04;

import java.text.MessageFormat;
import java.util.List;

import lesson_05.task_04.models.Gold;
import lesson_05.task_04.models.Island;
import lesson_05.task_04.models.TreasureMap;

public class Main {
    public static void main(String[] args) {
        List<Island> islands = Island.islandFactory(10);
        TreasureMap map = new TreasureMap(islands.get(4).getName(), 523);
        findTreasure(islands, map);
        printExpencivestTreasure(islands);
        printEmptyTreasures(islands);
    }

    public static void findTreasure(List<Island> islands, TreasureMap map){

        for (Island island : islands) {

            if(island.getName().equalsIgnoreCase(map.getIslandName())){

                if(!island.getTreasure().isEmpty()){
                    System.out.println(MessageFormat.format("На острове \"{0}\" найден сундук, " +
                    "содержащий в себе {1}, в количестве {2}, на общую стоимость в {3}",
                    map.getIslandName(),
                    island.getTreasure().getClass().getSimpleName(),
                    island.getTreasure().getQuantity(),
                    island.getTreasure().getPrice()));
                    return;
                } else {
                    System.out.println("Сундук оказался пуст!");
                    return;
                }
            }
        }

        System.out.println("Такого острова не существует!");
    }

    public static void printEmptyTreasures(List<Island> islands){
        StringBuilder sb = new StringBuilder();

        for (Island island : islands) {
            if(island.getTreasure().isEmpty()){
                sb.append(MessageFormat.format("\"{0}\", ",island.getName()));
            }
        }

        if(!sb.toString().isBlank()){
            System.out.println("Острова на которых находился пустой сундук: " + sb.toString().substring(0, sb.length() - 2));
        } else {
            System.out.println("Пустых сундуков не было");
        }
    }

    public static void printExpencivestTreasure(List<Island> islands){
        Island islandWithExpenciveTreasure = islands.get(0);
        for (int i = 1; i < islands.size(); i++) {
            if(islandWithExpenciveTreasure.getTreasure().getPrice() < islands.get(i).getTreasure().getPrice()){
                islandWithExpenciveTreasure = islands.get(i);
            }
        }
        System.out.println(MessageFormat.format("Самые богатые сокровища находились на острове \"{0}\". " + 
        " Сундук содержал {1}, в количестве {2}, на общую сумму в {3}", 
        islandWithExpenciveTreasure.getName(),
        islandWithExpenciveTreasure.getTreasure().getClass().getSimpleName(),
        islandWithExpenciveTreasure.getTreasure().getQuantity(),
        islandWithExpenciveTreasure.getTreasure().getPrice()));
    }
}