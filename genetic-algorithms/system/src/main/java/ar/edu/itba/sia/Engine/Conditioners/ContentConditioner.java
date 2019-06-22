package ar.edu.itba.sia.Engine.Conditioners;

import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Generics.Conditioner;

import java.util.List;

public class ContentConditioner implements Conditioner<GameCharacter> {
    private final long cantGenerations;
    private double maxFitness;

    public ContentConditioner(long cantGenerations) {
        this.cantGenerations = cantGenerations;
    }
    @Override
    public boolean shouldEvolve(List<GameCharacter> previousGeneration, List<GameCharacter> currentGeneration, long generation) {
        if (generation == 0) {
            maxFitness = 0;
            currentGeneration.forEach(individual -> {
                double fitness = individual.getFitness();
                if (compare(fitness, maxFitness)) {
                    maxFitness = fitness;
                }
            });
            return true;
        }
        if (generation % cantGenerations != 0) {
            return true;
        }
        boolean wasChanged = false;
        for (GameCharacter individual : currentGeneration) {
            double fitness = individual.getFitness();
            if (compare(fitness, maxFitness)) {
                maxFitness = fitness;
                wasChanged = true;
            }
        }
        return wasChanged;
    }

    private boolean compare(double fitness1, double fitness2){
        return fitness1 - fitness2 > 0.0001;
    }

    @Override
    public String getClassName(){
        return "ContentConditioner";
    }
}
