package ar.edu.itba.sia.Exceptions;

public class IllegalChromoseException extends RuntimeException {
    public IllegalChromoseException(int size){
        super("Chromosome size must be " + size);
    }
}
