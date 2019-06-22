package ar.edu.itba.sia.Generics;

import java.util.List;

public interface Replacer<T extends Species> {
    List<T> getParents(List<T> population, int populationLimit, long generation);
    double getGenerationalGapRatio();
    List<T> mix(List<T> originalPopulation, List<T> newChildren, long generation);
    String getClassName();
}