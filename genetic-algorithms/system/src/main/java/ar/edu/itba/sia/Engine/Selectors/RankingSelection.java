package ar.edu.itba.sia.Engine.Selectors;

import ar.edu.itba.sia.Generics.Selector;
import ar.edu.itba.sia.Generics.Species;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RankingSelection<T extends Species> implements Selector<T> {

    public RankingSelection() {
    }

    @Override
    public List<T> select(List<T> population, int populationLimit, long generation) {
        int popuSize=population.size();
        double sum=0;
        double[] relative=new double[popuSize];
        double[] accum=new double[popuSize];
        List<T> rta=new LinkedList<>();
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        double fitnessSum=0;
        //calculo la suma del fitness ver de hacer con stream
        for(int i=1;i<=popuSize;i++){
            fitnessSum+=i;
        }
        //ordeno de menor a mayor
        List<T> fitnessOrderBy = population.stream()
                .sorted(Comparator.comparing(T::getFitness))
                .collect(Collectors.toList());

        //cada T va a tener su fitness=lugar en el array(el primero un 1, el segundo un 2)
        //armo array con la relativa
        for(int i=0;i<popuSize;i++){
            relative[i]=(i+1)/fitnessSum;
        }

        //calculo acumulada
        for(int i=0;i<popuSize;i++){
            sum+=relative[i];
            accum[i]=sum;
        }

        for(int i=0;i<populationLimit;i++){ //me genera los r
            //ver si anda bien random
            double r=rnd.nextDouble();
            double aux;
            int j=0;
            for(j=0;j<popuSize && accum[j]<r;j++){ //chequeo cual cumple el r
                aux=accum[j];
            }
            rta.add(fitnessOrderBy.get(j));
        }
        return rta;
    }

    @Override
    public String getClassName() {
        return "RankingSelection";
    }
}
