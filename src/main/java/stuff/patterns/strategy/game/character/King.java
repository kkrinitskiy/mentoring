package stuff.patterns.strategy.game.character;

import stuff.patterns.strategy.game.weapon.KnifeBehavior;
import stuff.patterns.strategy.game.weapon.WeaponBehavior;

public class King extends Character {
    public King() {
        weaponBehavior = new KnifeBehavior();
    }
}
