package ar.edu.itba.sia.Engine.Combinators;

import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Generics.Combinator;
import ar.edu.itba.sia.Generics.Couple;

import java.util.Random;

public class UniformCross extends Cross implements Combinator<GameCharacter> {


    public UniformCross(double probability){
        super(probability);
    }

    Random rand;
    private int length;
    private int chromosomeLength;
    @Override
    public Couple<GameCharacter> breed(Couple<GameCharacter> parents) {
        rand = new Random();
        return swapInRange(parents.getThingOne(), parents.getThingTwo());
    }

    private Couple<GameCharacter> swapInRange(GameCharacter thingOne, GameCharacter thingTwo) {
        GameCharacter offspring1, offspring2;
        Object[] newChromosome1 = thingOne.getChromosome().clone();
        Object[] newChromosome2 = thingTwo.getChromosome().clone();

        //randomIndex is 1-based index, while array access is 0-based.
        for(int i = 0; i < thingTwo.getChromosome().length; i++){
            if(rand.nextDouble()<0.5) {
                Object aux = newChromosome1[i];
                newChromosome1[i] = newChromosome2[i];
                newChromosome2[i] = aux;
            }
        }
        offspring1 = new GameCharacter(thingOne.getProfession(), newChromosome1);
        offspring2 = new GameCharacter(thingTwo.getProfession(), newChromosome2);

        return new Couple<>(offspring1, offspring2);
    }

    @Override
    public String getClassName() {
        return "AnnualCross";
    }
}
