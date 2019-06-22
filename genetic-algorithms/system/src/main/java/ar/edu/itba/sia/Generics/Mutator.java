package ar.edu.itba.sia.Generics;

public interface Mutator<T extends Species> {

    double MIN_HEIGHT = 1.3;
    double MAX_HEIGHT = 2;


    T mutate(T individual);

    boolean shouldMutate(T individual, long generation);

    String getClassName();
}
