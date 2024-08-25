package stuff.patterns.decorator.starbuzz;

import stuff.patterns.decorator.starbuzz.beverages.*;

import stuff.patterns.decorator.starbuzz.condiments.*;


public class StarBuzz {
    public static void main(String[] args) {

        Beverage beverage = new Espresso();

        System.out.println(beverage);

        Beverage beverage1 = new DarkRoast();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Mocha(beverage1);
        beverage1 = new Whip(beverage1);

        System.out.println(beverage1);

        Beverage beverage2 = new HouseBlend();
        beverage2 = new Soy(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);

        System.out.println(beverage2);



    }
}
