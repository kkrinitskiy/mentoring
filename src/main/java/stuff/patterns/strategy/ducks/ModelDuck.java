package stuff.patterns.strategy.ducks;

import stuff.patterns.strategy.ducks.fly.FlyNoWay;
import stuff.patterns.strategy.ducks.quack.Quack;

public class ModelDuck extends Duck{

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm model of duck");
    }
}
