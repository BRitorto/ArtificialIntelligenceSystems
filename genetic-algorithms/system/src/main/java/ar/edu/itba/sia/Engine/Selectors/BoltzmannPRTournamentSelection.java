package ar.edu.itba.sia.Engine.Selectors;

import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Generics.Selector;

import java.util.List;

public class BoltzmannPRTournamentSelection implements Selector<GameCharacter> {

    @Override
    public List<GameCharacter> select(List<GameCharacter> population, int populationLimit, long generation) {
        double boltzDenom = getGenerationAverage(population, generation);
        double temp = getTemperature(generation);

        ProbabilisticTournamentSelection<GameCharacter> pr = new ProbabilisticTournamentSelection<>();

        return pr.selectBoltzmann(population, populationLimit, generation, boltzDenom, temp);
    }

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

    @Override
    public String getClassName() {
        return "BoltzmannPRTournamentSelection";
    }
}
