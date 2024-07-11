package stuff.patterns.factory.ai_hw;

public class PepperoniPizza implements Food{
    @Override
    public String getName() {
        return "PepperoniPizza";
    }

    @Override
    public int getPrice() {
        return 40;
    }
}
