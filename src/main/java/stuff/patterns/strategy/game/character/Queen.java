package stuff.patterns.strategy.game.character;

import stuff.patterns.strategy.game.weapon.KnifeBehavior;

public class Queen extends Character{
    public Queen() {
        weaponBehavior = new KnifeBehavior();
    }
}
