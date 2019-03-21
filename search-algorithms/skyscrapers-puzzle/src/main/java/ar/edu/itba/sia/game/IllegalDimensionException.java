package ar.edu.itba.sia.game;

public class IllegalDimensionException extends RuntimeException {
    IllegalDimensionException(){
        super("Wrong dimensions of side numbers");
    }
}
