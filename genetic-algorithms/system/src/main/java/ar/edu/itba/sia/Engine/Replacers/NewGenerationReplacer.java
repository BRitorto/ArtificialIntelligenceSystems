package ar.edu.itba.sia.Engine.Replacers;

import ar.edu.itba.sia.Generics.Replacer;

import java.util.List;

public class NewGenerationReplacer implements Replacer {
    @Override
    public List getParents(List population, int populationLimit, long generation) {
        if (population.size() > populationLimit) {
            return population.subList(0, populationLimit);
        }
        return population;
    }

    @Override
    public double getGenerationalGapRatio() {
        return 1;
    }

    @Override
    public List mix(List originalPopulation, List newChildren, long generation) {
        return newChildren;
    }

    @Override
    public String getClassName(){
        return "NewGenerationReplacer";
    }
}
