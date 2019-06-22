package ar.edu.itba.sia.Engine.Conditioners;

import ar.edu.itba.sia.Generics.Conditioner;
import ar.edu.itba.sia.Generics.Species;

import java.util.*;

public class StructureConditioner<T extends Species> implements Conditioner {
    private final double delta;

    public StructureConditioner(double delta) {
        this.delta = delta;
    }

    @Override
    public boolean shouldEvolve(List previousGeneration, List currentGeneration, long generation) {

        List<T> notContained = new LinkedList<>(previousGeneration);
        notContained.removeAll(currentGeneration);
        return notContained.size() > this.delta;
        // ver si esto se puede hacer mas eficiente. hay que chequear cuantos elementos de una de las listas NO esta en la otra
    }

    @Override
    public String getClassName(){
        return "StructureConditioner";
    }
}
