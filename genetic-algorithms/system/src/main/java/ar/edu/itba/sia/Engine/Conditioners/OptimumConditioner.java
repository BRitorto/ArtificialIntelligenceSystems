package ar.edu.itba.sia.Engine.Conditioners;

import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Generics.Conditioner;

import java.util.List;

public class OptimumConditioner implements Conditioner<GameCharacter> {
    private final double requiredFitness;

    public OptimumConditioner(double requiredFitness) {
        this.requiredFitness = requiredFitness;
    }
    @Override
    public boolean shouldEvolve(List<GameCharacter> previousGeneration, List<GameCharacter> currentGeneration, long generation) {
        double maxFitness = currentGeneration
                .stream()
                .mapToDouble(v -> v.getFitness())
                .max().getAsDouble();
        return !compare(requiredFitness, maxFitness); //returns true si todavia no llegue al required fitness y tengo que seguir
    }

    private boolean compare(double fitness1, double fitness2){
        return (fitness1 - fitness2) < 0.0001;
    }

    @Override
    public String getClassName(){
        return "OptimumConditioner";
    }
}
