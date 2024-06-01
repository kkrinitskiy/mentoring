package stuff.patterns.strategy.game.weapon;

public class BowAndArrowBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("Use bow and arrow");
    }
}
