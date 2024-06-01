package stuff.patterns.strategy.game.character;

import stuff.patterns.strategy.game.weapon.AxeBehavior;

public class Troll extends Character{
    public Troll() {
        weaponBehavior = new AxeBehavior();
    }
}
