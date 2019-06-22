package ar.edu.itba.sia.Engine.Selectors;

import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Generics.Selector;

import java.util.List;

public class BoltzmannRouletteSelection extends BoltzmannSelection implements Selector<GameCharacter> {
    @Override
    public List<GameCharacter> select(List<GameCharacter> population, int populationLimit, long generation) {
        double boltzDenom = getGenerationAverage(population, generation);
        double temp = getTemperature(generation);
        RouletteSelection<GameCharacter> rs = new RouletteSelection<>();

        return rs.selectBoltzmann(population, populationLimit, generation, boltzDenom, temp);
    }

    @Override
    public String getClassName() {
        return "BoltzmannRouletteSelection";
    }
}
