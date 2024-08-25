package stuff.patterns.factory.ai_hw;

public class ItalianRestaurant implements Restaurant{
    @Override
    public Food createFood(String type) {
        switch (type){
            case "PepperoniPizza" -> {
                return new PepperoniPizza();
            }
            case "MargheritaPizza" -> {
                return new MargheritaPizza();
            }
            default -> {
                return null;
            }
        }
    }
}
