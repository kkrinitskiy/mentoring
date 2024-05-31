package stuff.functionalInterfaces;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import stuff.functionalInterfaces.menu.CaloricLevel;
import stuff.functionalInterfaces.menu.Dish;
import stuff.functionalInterfaces.menu.Type;

public class Main {
    public static void main(String[] args) {
//        Stream.iterate(new int[]{0,1},
//                t -> new int[]{t[1], t[0] + t[1]}).limit(10)
//                .mapToInt(t -> t[0]).forEach(System.out::println);
//

        List<Dish> menu = new ArrayList<>();

        menu.add(new Dish("salmon", Type.FISH, CaloricLevel.NORMAL));
        menu.add(new Dish("prawns", Type.FISH, CaloricLevel.DIET));
        menu.add(new Dish("pork", Type.MEAT, CaloricLevel.FAT));
        menu.add(new Dish("beef", Type.MEAT, CaloricLevel.NORMAL));
        menu.add(new Dish("chicken", Type.MEAT, CaloricLevel.DIET));
        menu.add(new Dish("pizza", Type.OTHER, CaloricLevel.NORMAL));
        menu.add(new Dish("french fries", Type.OTHER, CaloricLevel.NORMAL));
        menu.add(new Dish("fruit", Type.OTHER, CaloricLevel.DIET));
        menu.add(new Dish("rice", Type.OTHER, CaloricLevel.DIET));


        Map<Type, List<Dish>> menuByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));

        for (Map.Entry<Type, List<Dish>> typeListEntry : menuByType.entrySet()) {
            System.out.println(typeListEntry.getKey() + " --- " +typeListEntry.getValue());
        }

        Map<Type, Map<CaloricLevel, List<Dish>>> menuByTypeAndCalories = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(Dish::getLevel)));

        System.out.println();

        for (Map.Entry<Type, Map<CaloricLevel, List<Dish>>> typeMapEntry : menuByTypeAndCalories.entrySet()) {
            for (Map.Entry<CaloricLevel, List<Dish>> caloricLevelListEntry : typeMapEntry.getValue().entrySet()) {
                System.out.println(typeMapEntry.getKey() + " --- "
                        + caloricLevelListEntry.getKey() + " --- "
                        + caloricLevelListEntry.getValue());
            }

        }

        System.out.println();

//        Map<Type, Long> countingByType = menu.stream()
//                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
//
//        countingByType.forEach((a, b) -> System.out.println(a + "  " + b));




    }
}
