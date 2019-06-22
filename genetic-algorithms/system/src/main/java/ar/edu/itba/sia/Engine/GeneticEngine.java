package ar.edu.itba.sia.Engine;

import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Generics.*;
import ar.edu.itba.sia.Utils.DiversionGraph;
import ar.edu.itba.sia.Utils.Diversity;
import ar.edu.itba.sia.Utils.FitnessGraph;
import ar.edu.itba.sia.Utils.JsonManager;
import org.json.simple.JSONObject;

import java.util.*;

public class GeneticEngine<T extends Species> {
    private final Conditioner<T> conditioner;
    private final Combinator<T> combinator;
    private final Mutator<T> mutator;
    private final Replacer<T> replacer;
    private double maxFitnessAllGenerations;
    private T bestCharacter;
    private FitnessGraph graph;
    private DiversionGraph diversionGraph;
    private Diversity diversity;

    public GeneticEngine(Combinator<T> combinator, Mutator<T> mutator, Replacer<T> replacer, Conditioner<T> conditioner, Diversity diversity) {
        this.conditioner = conditioner;
        this.combinator = combinator;
        this.mutator = mutator;
        this.replacer = replacer;
        this.maxFitnessAllGenerations = 0;
        this.graph = new FitnessGraph();
        this.diversionGraph=new DiversionGraph();
        this.diversity = diversity;
    }

    public List<T> evolve(List<T> population) {
        JSONObject data = JsonManager.readJSON();
        assert data != null;
        long genCant = (long) data.get("GraphDiversityGenerations");


        List<T> previousPopulation;
        List<T> currentPopulation=population;
        long generation = 0;
        do{
            previousPopulation = currentPopulation;
            currentPopulation = getNextGeneration(currentPopulation, generation);
//            System.out.println("Generacion: ");
//            for(T t:currentPopulation){
//                System.out.println(t.getFitness());
//            }
//            System.out.println();

            if(generation%genCant==0){
                int[] div;
                div = diversity.calculateDiversity((List<GameCharacter>) previousPopulation,generation);
                diversionGraph.onGenerationDiversion(div[0],div[1],div[2],div[3],generation);
                //System.out.println(div[0] + " , "+ div[1] + " , "+ div[2]+ " , " + div[3]);
            }

            getFitness(currentPopulation,generation);
//            System.out.println("new gen");
        } while(conditioner.shouldEvolve(previousPopulation, currentPopulation, generation++));
        System.out.println("El mejor caracter es: ");
        GameCharacter characterBest= (GameCharacter) bestCharacter;
        System.out.print(characterBest.toString());



        return currentPopulation;
    }

    private void getFitness(List<T> currentPopulation,long generation) {
        double max = 0,min = Double.MAX_VALUE, avg = 0, aux, sum = 0;
        for (T t : currentPopulation) {
            aux = t.getFitness();
            sum += aux;
            if(aux>maxFitnessAllGenerations){
                maxFitnessAllGenerations=aux;
                bestCharacter=t;
            }
            if (aux > max) {
                max = aux;
            }
            if (aux < min) {
                min = aux;
            }
        }

        graph.onGeneration(max,sum/currentPopulation.size(),min,(int)generation);
    }

    private List<T> getNextGeneration(List<T> population, long generation) {

        int populationGap = (int) (this.replacer.getGenerationalGapRatio() * population.size());
        List<T> parents = replacer.getParents(population, populationGap, generation);
        List<Couple<T>> couples = combinator.pickCouples(parents, (int) Math.ceil(populationGap/2));

        List<T> children = new LinkedList<>();
        couples.forEach(couple -> {
            if (combinator.shouldBreed(couple, generation)) {
                long curr;
                Couple<T> offspring = combinator.breed(couple);
                T offspring1 = offspring.getThingOne();
                T offspring2 = offspring.getThingTwo();

                if (mutator.shouldMutate(offspring1, generation)) {
                    mutator.mutate(offspring1);
                }
                if (mutator.shouldMutate(offspring2, generation)) {
                    mutator.mutate(offspring2);
                }
                children.add(offspring1);
                children.add(offspring2);
//                System.out.println("a>>>>"+offspring1.getFitness());
//                System.out.println("b>>>>"+offspring2.getFitness());
             // maxFitness = Math.max(maxFitness,Math.max(offspring1.getFitness(),offspring2.getFitness()));
             // System.out.println("Max: "+ maxFitness);
            } else {
                children.add(couple.getThingOne());
                children.add(couple.getThingTwo());
            }
        });
        return replacer.mix(population, children, generation).subList(0, population.size());
    }
}
