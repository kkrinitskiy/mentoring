package lesson_06.lambda;

public class HighTemp {
    int highTemp;
    public HighTemp(int ht) {highTemp = ht;}

    public boolean sameTemp(HighTemp ht2){
        return highTemp == ht2.highTemp;
    }

    public boolean lessThanTemp(HighTemp ht2){
        return ht2.highTemp > highTemp;
    }

}
