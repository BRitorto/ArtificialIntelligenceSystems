package ar.edu.itba.sia.Engine.Selectors;

import ar.edu.itba.sia.Generics.Selector;
import ar.edu.itba.sia.Generics.Species;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RouletteSelection<T extends Species> implements Selector<T> {

    @Override
    public List<T> select(List<T> population, int populationLimit, long generation) {
        double sum = 0;
        int popuSize=population.size();
        double[] relative=new double[popuSize];
        double[] accum = new double[popuSize];
        double fitnessSum=0;

        //calculo la suma del fitness ver de hacer con stream
        for (T t : population) {
            fitnessSum += t.getFitness();
        }

        //calculo la relativa
        for (int i = 0; i <popuSize; i++){
            relative[i]=population.get(i).getFitness()/fitnessSum;
        }

        //calculo la acumulada
        for (int i = 0; i < popuSize; i++) {
            sum += relative[i];
            accum[i] = sum;
        }
        return chooseElements(population,populationLimit, popuSize, accum);
    }

    private List<T> chooseElements(List<T> population,int populationLimit, int popuSize, double[] accum) {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        List<T> rta = new LinkedList<>();

        for (int i = 0; i < populationLimit; i++){ //me genera los r
            //ver si anda bien random
            double r = rnd.nextDouble();
            double aux;
            int j;
            for (j = 0; j < popuSize && accum[j] < r; j++) { //chequeo cual cumple el r
                aux = accum[j];
            }
            rta.add(population.get(j));
        }
        return rta;
    }

    public List<T> selectBoltzmann(List<T> population, int populationLimit, long generation, double boltzDenom, double temperature){
        double sum = 0;
        int popuSize=population.size();
        double[] accum = new double[popuSize];
        List<T> rta=new LinkedList<>();
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        double[] relative=new double[popuSize];
        double fitnessSum=0;

        //calculo la suma del fitness ver de hacer con stream
        for (T t : population) {
            fitnessSum += t.getBoltzmannFitness(boltzDenom, temperature);
        }

        //calculo la relativa
        for (int i = 0; i <popuSize; i++){
            relative[i]=population.get(i).getBoltzmannFitness(boltzDenom, temperature)/fitnessSum;
        }

        //calculo la acumulada
        for (int i = 0; i < popuSize; i++) {
            sum += relative[i];
            accum[i] = sum;
        }
        return chooseElements(population,populationLimit, popuSize, accum);
    }

    @Override
    public String getClassName(){
        return "RouletteSelection";
    }

}
