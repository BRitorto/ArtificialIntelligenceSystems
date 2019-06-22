package ar.edu.itba.sia.Engine.Combinators;

import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Generics.Combinator;
import ar.edu.itba.sia.Generics.Couple;

import java.util.Random;

public class AnnularCross extends Cross implements Combinator<GameCharacter> {


    private int fromIndex;
    private int length;
    private int chromosomeLength;

    public AnnularCross(double probability){
        super(probability);
    }

    @Override
    public Couple<GameCharacter> breed(Couple<GameCharacter> parents) {
        fromIndex = new Random().nextInt(parents.getThingOne().getChromosome().length - 1) + 1; //Number between 1 and size-1
        length = new Random().nextInt((parents.getThingOne().getChromosome().length-1)/2) + 1;
        chromosomeLength = parents.getThingOne().getChromosome().length -1;
        return swapInRange(parents.getThingOne(), parents.getThingTwo());

    }

    private Couple<GameCharacter> swapInRange(GameCharacter thingOne, GameCharacter thingTwo) {
        GameCharacter offspring1, offspring2;
        Object[] newChromosome1 = thingOne.getChromosome().clone();
        Object[] newChromosome2 = thingTwo.getChromosome().clone();

        //randomIndex is 1-based index, while array access is 0-based.
        for(int i =fromIndex-1, j=0; j <= length; j++,i++){
            i = i%chromosomeLength;
            Object aux = newChromosome1[i];
            newChromosome1[i] = newChromosome2[i];
            newChromosome2[i] = aux;

        }
        offspring1 = new GameCharacter(thingOne.getProfession(), newChromosome1);
        offspring2 = new GameCharacter(thingTwo.getProfession(), newChromosome2);

        return new Couple<>(offspring1, offspring2);
    }


    @Override
    public String getClassName() {
        return "AnnularCross";
    }

    public int getFromIndex(){
        return fromIndex;
    }

    public int getLength(){
        return length;
    }

    public int getToIndex(){
        return (fromIndex+length)%chromosomeLength;
    }

    public int getChromosomeLength() {
        return chromosomeLength;
    }
}
