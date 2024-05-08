package lesson_04.hw4.transport_company_core;
import java.text.MessageFormat;
import java.util.*;
import lesson_04.hw4.HWUtils;

public class TransportCompany {
    List<Container> emptyConatiners;
    List<Container> fullConatiners = new ArrayList<>(0);


    public TransportCompany(int containerCount) {
        emptyConatiners = new ArrayList<>();
        for (int i = 0; i < containerCount; i++) {
            emptyConatiners.add(
                new Container(
                    HWUtils.getRandomWidth(),
                    HWUtils.getRandomHight(), 
                    HWUtils.getRandomLength()
                    ));
        }
    }

    public void loadingItem(Transportable item){
        for (int i = 0; i < emptyConatiners.size(); i++) {
            if(emptyConatiners.get(i).loadingItem(item)){
                fullConatiners.add(emptyConatiners.get(i));
                return;
            }
        }
        System.out.println(MessageFormat.format("Не было найдено подходящих контейнеров для {0}", item.getClass().getSimpleName()));
    }

    public List<Container> getFullConatiners() {
        return fullConatiners;
    }

}
