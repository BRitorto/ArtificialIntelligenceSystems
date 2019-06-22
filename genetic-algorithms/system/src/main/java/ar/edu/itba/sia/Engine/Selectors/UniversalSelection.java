package ar.edu.itba.sia.Engine.Selectors;

import ar.edu.itba.sia.Generics.Selector;
import ar.edu.itba.sia.Generics.Species;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class UniversalSelection<T extends Species> implements Selector<T> {

    public UniversalSelection() {
    }


    @Override
    public List<T> select(List<T> population, int populationLimit, long generation) {
        double sum = 0,fitnessSum=0;
        int popuSize=population.size();
        double[] accum = new double[popuSize];
        double[] relative=new double[popuSize];

        List<T> rta=new LinkedList<>();
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        double r=rnd.nextDouble();

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

        for(int i=0;i<populationLimit;i++){ //voy generando ri
            //ver si anda bien random
            double raux=((r+(i+1)-1)/populationLimit);
            double aux;
            int j=0;
            for(j=0;j<population.size() && accum[j]<raux;j++){ //chequeo cual cumple el r
                aux=accum[j];
            }
            rta.add(population.get(j));
        }
        return rta;
    }

    @Override
    public String getClassName(){
        return "UniversalSelection";
    }
}
