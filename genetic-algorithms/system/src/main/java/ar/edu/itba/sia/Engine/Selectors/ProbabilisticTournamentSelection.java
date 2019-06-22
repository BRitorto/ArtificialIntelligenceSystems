package ar.edu.itba.sia.Engine.Selectors;

import ar.edu.itba.sia.Generics.Selector;
import ar.edu.itba.sia.Generics.Species;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ProbabilisticTournamentSelection<T extends Species> implements Selector<T> {

    public ProbabilisticTournamentSelection() {
    }

    @Override
    public List<T> select(List<T> population, int populationLimit, long generation) {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        List<T> rta=new LinkedList<>();

        for(int i=0;i<populationLimit;i++){
            T m1=population.get(rnd.nextInt(population.size()));
            T m2=population.get(rnd.nextInt(population.size()));

            double r=rnd.nextDouble();

            if(r<0.75){
                if(m1.getFitness()>m2.getFitness()){
                    rta.add(m1);
                }
                else{
                    rta.add(m2);
                }
            }
            else{
                if(m1.getFitness()>m2.getFitness()){
                    rta.add(m2);
                }
                else{
                    rta.add(m1);
                }
            }
        }
        return rta;
    }

    public List<T> selectBoltzmann(List<T> population, int populationLimit, long generation, double boltzDenom, double temperature) {

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        List<T> rta=new LinkedList<>();

        for(int i=0;i<populationLimit;i++){
            T m1=population.get(rnd.nextInt(population.size()));
            T m2=population.get(rnd.nextInt(population.size()));

            double r=rnd.nextDouble();

            if(r<0.75){
                if(m1.getBoltzmannFitness(boltzDenom, temperature)>m2.getBoltzmannFitness(boltzDenom,temperature)){
                    rta.add(m1);
                }
                else{
                    rta.add(m2);
                }
            }
            else{
                if(m1.getBoltzmannFitness(boltzDenom,temperature)>m2.getBoltzmannFitness(boltzDenom,temperature)){
                    rta.add(m2);
                }
                else{
                    rta.add(m1);
                }
            }
        }
        return rta;
    }

    @Override
    public String getClassName(){
        return "ProbabilisticTournamentSelection";
    }
}
