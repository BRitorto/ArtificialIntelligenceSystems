package ar.edu.itba.sia.Generics;

public class Couple<T extends Species> {
    private final T thingOne;
    private final T thingTwo;

    public Couple(T thingOne, T thingTwo) {
        this.thingOne = thingOne;
        this.thingTwo = thingTwo;
    }

    public T getThingOne() {
        return thingOne;
    }

    public T getThingTwo() {
        return thingTwo;
    }
}
