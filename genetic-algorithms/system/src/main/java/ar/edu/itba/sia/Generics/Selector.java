package ar.edu.itba.sia.Generics;

import java.util.List;

public interface Selector<T extends Species> {
    List<T> select(List<T> population, int populationLimit, long generation);

    String getClassName();
}
