package ar.edu.itba.sia.Engine.Selectors;

import ar.edu.itba.sia.Game.GameCharacter;

import java.util.List;

public class BoltzmannSelection {

    public double getTemperature(long generation){
        return 1.0 / 0.001*(generation + 1) + 1;
    }

    public double getGenerationAverage(List<GameCharacter> population, long generation){
        double acum = 0;
        for(GameCharacter character : population){
            acum += Math.exp(character.getFitness() / getTemperature(generation));
        }
        return acum/population.size();
    }
}
