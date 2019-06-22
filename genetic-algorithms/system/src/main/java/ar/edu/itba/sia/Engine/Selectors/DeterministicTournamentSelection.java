package ar.edu.itba.sia.Engine.Selectors;

import ar.edu.itba.sia.Generics.Selector;
import ar.edu.itba.sia.Generics.Species;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DeterministicTournamentSelection<T extends Species> implements Selector<T> {

    @Override
    public List<T> select(List<T> population, int populationLimit, long generation) {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        List<T> rta=new LinkedList<>();
        int m = 2; //las diaspo dicen que puede ser 2 o 3

        for(int i=0;i<populationLimit;i++){
            T m1=population.get(rnd.nextInt(population.size()));
            T m2=population.get(rnd.nextInt(population.size()));
            if(m1.getFitness()>m2.getFitness()){
                rta.add(m1);
            }
            else {
                rta.add(m2);
            }
        }
        return rta;
    }

    @Override
    public String getClassName(){
        return "DeterministicTournamentSelection";
    }
}
