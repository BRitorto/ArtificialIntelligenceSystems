package ar.edu.itba.sia.Engine.Combinators;

import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Generics.Couple;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *  Parent class that has the behavior shared by every Cross algorithm
 */
public class Cross{

    private Random randomNumGenerator;
    private double probability;

    Cross(double probability){
        randomNumGenerator = new Random();
        randomNumGenerator.setSeed(System.currentTimeMillis());
        this.probability = probability;
    }

    /**
     * Recieves a List of potential parents and a maximum number of couples to be created.
     * It randomly generates as much couples as possible using the potentialParents list. It
     * will stop creating couples once it reaches the maximum number of couples created or if
     * it creates as many couples as potentialParents there are.
     *
     * @param potentialParents
     * @param couples
     * @return List of couples
     */

    public List<Couple<GameCharacter>> pickCouples(List<GameCharacter> potentialParents, int couples) {
        List<Couple<GameCharacter>> parentCouples = new LinkedList<>();
        int len = potentialParents.size();
        Random rand = new Random();
        int randNum;
        for (int i = 0; i< len && i<couples; i++) {
            randNum = rand.nextInt(len-1);
            parentCouples.add(new Couple<>(potentialParents.get(randNum), potentialParents.get((randNum+1)%len)));
        }
        return parentCouples;
    }

    /**
     * Returns a boolean based on the given probability that defines if the parents should breed or not.
     *
     * //TODO: It seems that the given parameters are not used. Should they be removed?
     * @param parents
     * @param generation
     * @return
     */
    public boolean shouldBreed(Couple<GameCharacter> parents, long generation) {

        return randomNumGenerator.nextDouble() <= probability;
    }



    public Random getRandomNumGenerator(){
        return randomNumGenerator;
    }

    public double getProbability(){
        return probability;
    }
}
