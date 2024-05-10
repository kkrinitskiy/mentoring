package lesson_04.hw4.transport_company_core;

public enum Tax {
    ANIMAL(3.6), WEAPON(2.9), CAR(2.4);


    double rate;
    Tax(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
