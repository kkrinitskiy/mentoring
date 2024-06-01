package stuff.patterns.strategy.game.weapon;

public class SwordBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("Use sword");
    }
}
