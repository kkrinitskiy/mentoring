package stuff.patterns.decorator.starbuzz.beverages;

public abstract class Beverage {
    String description = "unknown beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();

    @Override
    public String toString() {
        return getDescription() + " " + cost();
    }
}
