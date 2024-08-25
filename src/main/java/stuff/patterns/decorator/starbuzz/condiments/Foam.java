package stuff.patterns.decorator.starbuzz.condiments;

import stuff.patterns.decorator.starbuzz.beverages.Beverage;

public class Foam extends CondimentDecorator{

    Beverage beverage;

    public Foam(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk Foam";
    }

    @Override
    public double cost() {
        return beverage.cost() + .10;
    }
}
