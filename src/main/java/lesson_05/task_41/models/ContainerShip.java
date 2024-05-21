package lesson_05.task_41.models;

import java.util.*;
import java.util.Map.Entry;

import lesson_05.task_41.Item;
import lesson_05.task_41.exceptions.EmptyContainer;
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
        if(!containers.isEmpty()){
            return containers.get(containers.size());
        }
        throw new EmptyContainerShip("Nothing to unload");
    }

    public List<Container<? extends Item>> getListContainers(){
        return new ArrayList<>(containers.values());
    }

    public Container<? extends Item> getContainerByNumber(int n) throws NoSuchIdContainerException{
        if(n >= containers.size() || n < 0){
            throw new NoSuchIdContainerException();
        }
        return containers.get(n);
    }

    public Container<? extends Item> getContainerItemBiggest() throws EmptyContainerShip{
        if(!containers.isEmpty()){
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


    /**
     * Отладочный метод для контроля количества контейнеров и их содержимого
     */
    public void printContainers(){

        System.out.println("\n*********************************************");
        System.out.println("всего контейнеров: " + containers.size());
        System.out.println("*********************************************");
        for(Entry<Integer, Container<? extends Item>> e : containers.entrySet()){
            System.out.println(e.getValue());
            System.out.println("*********************************************");
        }
    }


    /**
     * Метод возвращает переданное количество контейнеров, содержащих самый большой по объему груз.
     * <p>
     * Если количество требуемых контейнеров превышает количество контейнеров находящихся на корабле,
     * выбрасывается ошибка.
     * <p>
     *Идея следующая - копируем лист контейнеров корабля (чтобы случайно не отредактировать содержимое),
     *далее пробегаемся по нему столько раз сколько нам нужно контейнеров, каждую итерацию откладывая в
     * лист-результат контейнер с самым объемным содержимым, удаляя его из этого листа.
     *
     * @param containersCount
     * @return
     * @throws NotEnoughContainers
     */
    public ArrayList<Container<? extends Item>> getContainersListSortedByVolume(int containersCount) throws NotEnoughContainers{
        if(containersCount <= containers.size()){
            ArrayList<Container<? extends Item>> result = new ArrayList<>();

            Collection<Container<? extends Item>> containerCollection = containers.values();

            ArrayList<Integer> keys = new ArrayList<>();
            for (int i = 0; i < containers.size(); i++) {
                keys.add(i);
            }


            Container<? extends Item> biggest;

            for (int i = 0; i < containersCount; i++) {
                biggest = containers.get(0);

                int keyToRemove = -1;
                for (Integer key : keys) {
                    if (containers.get(key).getItemVolume() > biggest.getItemVolume()) {
                        biggest = containers.get(key);
                        keyToRemove = key;
                    }
                }
                result.add(biggest);
                keys.remove(keyToRemove);
            }

            return result;
        }else{
            throw new NotEnoughContainers("containersCount exceeds the existing one");
        }
    }

    /**
     *  Метод возвращает контейнер с самым маленьким по объему содержимым.
     *  Если корабль пуст - бросает ошибку
     * @return
     * @throws EmptyContainerShip
     */
    public Container<? extends Item> getContainerWithSmallestItem() throws EmptyContainerShip{
        if(containers.isEmpty()){
            throw new EmptyContainerShip("You can't get smallest container from ContainerShip. It's not enough containers");
        }
        Container<? extends Item> smallest = containers.get(0);

        for (Container<? extends Item> container : containers.values()) {
            if (container.getItemVolume() < smallest.getItemVolume()) {
                smallest = container;
            }
        }


        return smallest;
    }

    /**
     * Метод меняет содержимое контейнеров местами
     * @param firstContainerIndex
     * @param secondContainerIndex
     * @throws EmptyContainerShip
     */
    public <T extends Item>void unloadItemsAndSwap(int firstContainerIndex, int secondContainerIndex) throws EmptyContainerShip, EmptyContainer {

        if(containers.isEmpty()){
            throw new EmptyContainerShip("Nothing to swap! ContainerShip is empty");
        }

        Container<T> firstContainer = (Container<T>) containers.get(firstContainerIndex);
        Container<T> secondContainer = (Container<T>) containers.get(secondContainerIndex);

        if(firstContainer.isEmpty() || secondContainer.isEmpty()){
            throw new EmptyContainer();
        }

        T waitingForLoad = secondContainer.unloadItem();



        secondContainer.loadItem(firstContainer.unloadItem());
        firstContainer.loadItem(waitingForLoad);
    }

}
