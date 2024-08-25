package stuff.patterns.factory.ai_hw;

public class JapaneseRestaurant implements Restaurant{
    @Override
    public Food createFood(String type) {
        switch (type){
            case "PhiladelphiaRoll" -> {
                return new PhiladelphiaRoll();
            }
            case "CaliforniaRoll" -> {
                return new CaliforniaRoll();
            }
            default -> {
                return null;
            }
        }
    }
}
