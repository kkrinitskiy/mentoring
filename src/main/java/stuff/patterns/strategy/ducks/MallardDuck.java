package stuff.patterns.strategy.ducks;

import stuff.patterns.strategy.ducks.fly.FlyWithWings;
import stuff.patterns.strategy.ducks.quack.Quack;

public class MallardDuck extends Duck{

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm Mallard Duck");
    }


}
