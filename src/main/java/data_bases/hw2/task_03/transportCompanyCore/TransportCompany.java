package data_bases.hw2.task_03.transportCompanyCore;


import data_bases.hw2.task_03.entities.Transportable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransportCompany {
//    private final static int MIN_WIDTH = 200;     в условиях слишком маленький
//    private final static int MAX_WIDTH = 300;     в условиях слишком маленький
    private final static int MIN_WIDTH = 1500;
    private final static int MAX_WIDTH = 2000;
//    private final static int MIN_HEIGHT = 300;    в условиях слишком маленький
//    private final static int MAX_HEIGHT = 400;    в условиях слишком маленький
    private final static int MIN_HEIGHT = 1500;
    private final static int MAX_HEIGHT = 2000;
    private final static int MIN_LENGTH = 3000;
    private final static int MAX_LENGTH = 6000;

    private List<Container> containers = new ArrayList<>();
    public TransportCompany(int containersCount) {
        for (int i = 0; i < containersCount; i++) {
            Random r = new Random();
            containers.add(new Container(r.nextInt(MIN_WIDTH, MAX_WIDTH + 1),
                    r.nextInt(MIN_HEIGHT, MAX_HEIGHT + 1),
                    r.nextInt(MIN_LENGTH, MAX_LENGTH + 1)));
        }
    }

    public boolean loadingItem(Transportable item) {
        Container container = findContainer(item);
        if (container != null) {
            container.loadingItem(item);
            System.out.println("Успешно погружено: " + item);
            return true;
        } else {
            System.out.println("Подходящего контейнера нет для " + item);
            return false;
        }
    }

    public Container findContainer(Transportable item) {
        Container result = null;
        for (Container container : containers) {
            if(result == null && container.canTransport(item)){
                result = container;
            }else if(result != null && container.canTransport(item) && container.getVolume() < result.getVolume()){
                result = container;
            }
        }
        return result;
    }
}
