package ar.edu.itba.sia.Engine.Selectors;

import ar.edu.itba.sia.Generics.Selector;
import ar.edu.itba.sia.Generics.Species;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EliteSelection<T extends Species> implements Selector<T> {

    @Override
    public List<T> select(List<T> population, int populationLimit, long generation) {
        //magia con stream chequear
        List<T> rta = population.stream()
                .sorted(Comparator.comparing(T::getFitness).reversed())
                .collect(Collectors.toList());
        return rta.subList(0,populationLimit);
    }

    @Override
    public String getClassName(){
        return "EliteSelection";
    }
}
