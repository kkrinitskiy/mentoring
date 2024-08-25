package stuff.patterns.decorator.starbuzz.condiments;

import stuff.patterns.decorator.starbuzz.beverages.Beverage;

public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;
    @Override
    public double cost() {
        return 0;
    }

    public abstract String getDescription();
}
