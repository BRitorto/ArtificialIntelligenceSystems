package ar.edu.itba.sia.Engine.Replacers;

import ar.edu.itba.sia.Generics.Replacer;
import ar.edu.itba.sia.Generics.Selector;
import ar.edu.itba.sia.Generics.Species;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeepSomeAncestorsReplacer<T extends Species> implements Replacer<T> {
    private final Selector<T> selector1;
    private final Selector<T> selector2;
    private final Selector<T> selector3;
    private final Selector<T> selector4;
    private final double ratioA;
    private final double ratioB;

    public KeepSomeAncestorsReplacer(Selector<T> selector1, Selector<T> selector2, Selector<T> selector3,
                                     Selector<T> selector4, double ratioA, double ratioB) {
        this.selector1 = selector1;
        this.selector2 = selector2;
        this.selector3 = selector3;
        this.selector4 = selector4;
        this.ratioA = ratioA;
        this.ratioB = ratioB;
        if (ratioA < 0 || ratioA > 1) {
            throw new IllegalArgumentException("Ratio must be 0 <= ratio <= 1");
        }
        if (ratioB < 0 || ratioB > 1) {
            throw new IllegalArgumentException("Ratio must be 0 <= ratio <= 1");
        }
    }

    @Override
    public List<T> getParents(List<T> population, int populationLimit, long generation) {
        int populationSizeFirst = (int) (populationLimit * ratioA);
        return Stream.concat(selector1.select(population, populationSizeFirst, generation).stream(),
                selector2.select(population, populationLimit-populationSizeFirst, generation).stream())
                .collect(Collectors.toList());
    }

    @Override
    public double getGenerationalGapRatio() {
        return ratioA;
    }

    @Override
    public List<T> mix(List<T> originalPopulation, List<T> newChildren, long generation) {
        int kComplement = originalPopulation.size() - newChildren.size();
        int populationSizeSecond = (int) (kComplement * ratioB);
        List<T> ancestorsToKeep = Stream.concat(selector3.select(originalPopulation, populationSizeSecond, generation).stream(),
                selector4.select(originalPopulation, kComplement-populationSizeSecond, generation).stream())
                .collect(Collectors.toList());
        newChildren.addAll(ancestorsToKeep);
        return newChildren;
    }

    @Override
    public String getClassName(){
        return "KeepSomeAncestorsReplacer";
    }
}
