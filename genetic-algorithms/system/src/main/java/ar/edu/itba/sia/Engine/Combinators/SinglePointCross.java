package ar.edu.itba.sia.Engine.Combinators;

import ar.edu.itba.sia.Generics.Combinator;
import ar.edu.itba.sia.Generics.Couple;
import ar.edu.itba.sia.Game.GameCharacter;
import java.util.Random;

public class SinglePointCross extends Cross implements Combinator<GameCharacter>{

    public SinglePointCross(double probability){
        super(probability);
    }

    // This value is saved in a variable for testing purposes
    private int randomIndex;
    @Override
    public Couple<GameCharacter> breed(Couple<GameCharacter> parents) {
        randomIndex = new Random().nextInt(parents.getThingOne().getChromosome().length - 1) + 1; //Number between 1 and size-1
        return swapFromIndex(parents.getThingOne(), parents.getThingTwo());
    }

    private Couple<GameCharacter> swapFromIndex(GameCharacter thingOne, GameCharacter thingTwo) {
        GameCharacter offspring1, offspring2;
        Object[] newChromosome1 = thingOne.getChromosome().clone();
        Object[] newChromosome2 = thingTwo.getChromosome().clone();

        //randomIndex is 1-based index, while array access is 0-based.
        for(int i = randomIndex-1; i < thingTwo.getChromosome().length; i++){
            Object aux = newChromosome1[i];
            newChromosome1[i] = newChromosome2[i];
            newChromosome2[i] = aux;
        }

        offspring1 = new GameCharacter(thingOne.getProfession(), newChromosome1);
        offspring2 = new GameCharacter(thingTwo.getProfession(), newChromosome2);
        return new Couple<>(offspring1, offspring2);
    }


    public int getRandomIndex() {
        return randomIndex;
    }

    @Override
    public String getClassName(){
        return "SinglePointCross";
    }
}
