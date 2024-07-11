package stuff.patterns.factory.ai_hw;

public class MargheritaPizza implements Food {
    @Override
    public String getName() {
        return "MargheritaPizza";
    }

    @Override
    public int getPrice() {
        return 30;
    }
}
