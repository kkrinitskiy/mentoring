package lesson_05.task_41.models;

import java.util.*;
import java.util.Map.Entry;

import lesson_05.task_41.Item;
import lesson_05.task_41.exceptions.EmptyContainerShip;
import lesson_05.task_41.exceptions.NoSuchIdContainerException;
import lesson_05.task_41.exceptions.NotEnoughContainers;

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
    
    public Container<? extends Item> unloadContainer() throws EmptyContainerShip{
        if(containers.size() > 0){
            return containers.get(containers.size());
        }
        throw new EmptyContainerShip("Nothing to unload");
    }

    public List<Container<? extends Item>> getListContainers(){
        List<Container<? extends Item>> list = new ArrayList<>();
        for(Container<? extends Item> container : containers.values()){
            list.add(container);
        }
        return list;
    }

    public Container<? extends Item> getConteinerByNumber(int n) throws NoSuchIdContainerException{
        if(n >= containers.size() || n < 0){
            throw new NoSuchIdContainerException();
        }
        return containers.get(n);
    }

    public Container<? extends Item> getContainerItemBiggest() throws EmptyContainerShip{
        if(containers.size() > 0){
            Container<? extends Item> biggest = containers.get(0);
            for(Entry<Integer, Container<? extends Item>> e : containers.entrySet()){
                if(e.getValue().getVolume() > biggest.getVolume()){
                    biggest = e.getValue();
                }
            }

            return biggest;
        } else {
            throw new EmptyContainerShip("You can't get biggest container from ContainerShip. There is no one container on board");
        }
    }


    public int getCountOfContainers(){
        return containers.size();
    }

    public int getFreePlaces(){
        return maxCountContainer - containers.size();
    }

    public ArrayList<Container<? extends Item>> getContainersListSortedByVolume(int containersCount) throws NotEnoughContainers{
        if(containersCount <= containers.size()){
            ArrayList<Container<? extends Item>> result = new ArrayList<>();
            
            for (int i = 0; i < containersCount; i++) {

                Container<? extends Item> biggestContainer = containers.get(1);
                for (Container<? extends Item> container : containers.values()) {
                    if(!alreadyHasThisVolumeInList(result, container)){
                        biggestContainer = container;
                    }
                }

                if(!result.contains(biggestContainer)){
                    result.add(biggestContainer);
                }
            }
            return result;

        }else{
            throw new NotEnoughContainers("containersCount exceeds the existing one");
        }
    }


    private boolean alreadyHasThisVolumeInList(ArrayList<Container<? extends Item>> list, Container<? extends Item> containerToComparison){
        for (Container<? extends Item> container : list) {
            if (containerToComparison.getVolume()==container.getItemVolume()){
                return true;
            }
        }
        return false;
    }

}
