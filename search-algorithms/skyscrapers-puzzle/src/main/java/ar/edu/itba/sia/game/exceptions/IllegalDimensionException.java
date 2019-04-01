package ar.edu.itba.sia.game.exceptions;

public class IllegalDimensionException extends RuntimeException {
    public IllegalDimensionException(){
        super("Wrong dimensions of side numbers");
    }
}
