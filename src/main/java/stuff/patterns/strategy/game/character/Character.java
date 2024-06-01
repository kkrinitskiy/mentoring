package stuff.patterns.strategy.game.character;

import stuff.patterns.strategy.game.weapon.WeaponBehavior;

public abstract class Character {
    WeaponBehavior weaponBehavior;

    public void fight(){
        weaponBehavior.useWeapon();
    }

    public WeaponBehavior getWeaponBehavior() {
        return weaponBehavior;
    }

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }
}
