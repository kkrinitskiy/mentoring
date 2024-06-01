package stuff.patterns.strategy.game.weapon;

public class AxeBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("Use axe");
    }
}
