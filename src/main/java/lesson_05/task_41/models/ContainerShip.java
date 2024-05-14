package lesson_05.task_41.models;

import java.util.*;
import java.util.Map.Entry;

import lesson_05.task_41.Item;

public class ContainerShip {
    private final int maxCountContainer;
    private HashMap<Integer, Container<? extends Item>> containers;

    public ContainerShip(int maxCountContainer) {
        this.maxCountContainer = maxCountContainer;
        containers = new HashMap<>();
    }
    
    public boolean loadContainer(Container<? extends Item> container){
        if(containers.size() < maxCountContainer){
            containers.put(containers.size(), container);
            return true;
        }
        return false;
    }
    
    public Container<? extends Item> unloadContainer(){
        if(containers.size() > 0){
            return containers.get(containers.size());
        }
        return null;
    }

    public List<Container<? extends Item>> getListContainers(){
        ArrayList<Container<? extends Item>> list = (ArrayList<Container<? extends Item>>) containers.values();
        return list;
    }

    public Container<? extends Item> getConteinerByNumber(int n){
        if(n >= containers.size() || n < 0){
            return null;
        }
        return containers.get(n);
    }

    public Container<? extends Item> getContainerItemBigges(){
        if(containers.size() > 0){
            Container<? extends Item> biggest = containers.get(0);
            for(Entry<Integer, Container<? extends Item>> e : containers.entrySet()){
                if(e.getValue().getVolume() > biggest.getVolume()){
                    biggest = e.getValue();
                }
            }

            return biggest;
        } else {
            return null;
        }
    }

}
