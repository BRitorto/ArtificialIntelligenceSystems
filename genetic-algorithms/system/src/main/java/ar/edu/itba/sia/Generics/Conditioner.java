package ar.edu.itba.sia.Generics;

import java.util.List;

public interface Conditioner<T extends Species> {
    boolean shouldEvolve(List<T> previousGeneration, List<T> currentGeneration, long generation);
    String getClassName();
}
