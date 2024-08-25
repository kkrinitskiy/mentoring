package stuff.patterns.factory.ai_hw;

public class CaliforniaRoll implements Food{
    @Override
    public String getName() {
        return "CaliforniaRoll";
    }

    @Override
    public int getPrice() {
        return 10;
    }
}
