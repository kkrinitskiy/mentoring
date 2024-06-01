package stuff.patterns.strategy.ducks.fly;

public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Fly with a rocket");
    }
}
