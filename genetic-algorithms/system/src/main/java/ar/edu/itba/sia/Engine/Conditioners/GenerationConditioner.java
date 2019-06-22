package ar.edu.itba.sia.Engine.Conditioners;

import ar.edu.itba.sia.Generics.Conditioner;

import java.util.List;

public class GenerationConditioner implements Conditioner {
    private final long maxGenerations;

    public GenerationConditioner(long maxGenerations) {
        if (maxGenerations <= 0) {
            throw new IllegalArgumentException("Not enough generations!");
        }
        this.maxGenerations = maxGenerations;
    }
    @Override
    public boolean shouldEvolve(List prev, List curr, long generation) {
        return this.maxGenerations > generation;
    }

    @Override
    public String getClassName(){
        return "GenerationConditioner";
    }
}
