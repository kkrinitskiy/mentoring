package stuff.patterns.strategy.game.character;

import stuff.patterns.strategy.game.weapon.SwordBehavior;

public class Knight extends Character{
    public Knight() {
        weaponBehavior = new SwordBehavior();
    }
}
