package ar.edu.itba.sia.Engine.Crossover;

import ar.edu.itba.sia.Engine.Combinators.DoublePointCross;
import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Game.Profession;
import ar.edu.itba.sia.Generics.Couple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DoublePointCrossTest {
    private GameCharacter thingOne;
    private GameCharacter thingTwo;
    private Profession prof;

    @Before
    public void setUp(){
        prof = Profession.valueOf("ARCHER1");
        thingOne = CharacterGenerator.createCharacter(prof, 1);
        thingTwo = CharacterGenerator.createCharacter(prof, 6);
    }

    @Test
    public void breadWorksTest(){
        DoublePointCross dp = new DoublePointCross(0.5);

        Couple<GameCharacter> newCouple;
        newCouple = dp.breed(new Couple<>(thingOne, thingTwo));

        Assert.assertNotNull(newCouple);
        Assert.assertNotNull(newCouple.getThingOne());
        Assert.assertNotNull(newCouple.getThingTwo());
    }

    @Test
    public void breedSwapWorksCorrectly(){
        DoublePointCross dp = new DoublePointCross(0.5);

        Couple<GameCharacter> newCouple = dp.breed(new Couple<>(thingOne, thingTwo));


        for(int i = 0; i < dp.getFromIndex() - 1; i++){
            Assert.assertEquals(thingOne.getChromosome()[i], newCouple.getThingOne().getChromosome()[i]);
            Assert.assertEquals(thingTwo.getChromosome()[i], newCouple.getThingTwo().getChromosome()[i]);
        }


        for(int i = dp.getFromIndex()-1; i<= dp.getToIndex()-1; i++){
            Assert.assertEquals(thingOne.getChromosome()[i], newCouple.getThingTwo().getChromosome()[i]);
            Assert.assertEquals(thingTwo.getChromosome()[i], newCouple.getThingOne().getChromosome()[i]);
        }



        for(int i = dp.getToIndex(); i< newCouple.getThingTwo().getChromosome().length; i++){
            Assert.assertEquals(thingOne.getChromosome()[i], newCouple.getThingOne().getChromosome()[i]);
            Assert.assertEquals(thingTwo.getChromosome()[i], newCouple.getThingTwo().getChromosome()[i]);
        }


    /*
        System.out.println("From Index: " + dp.getFromIndex());
        System.out.println("To Index: " + dp.getToIndex());
        System.out.println("Parent1 " + Arrays.toString(thingOne.getChromosome()));
        System.out.println("Parent2 " + Arrays.toString(thingTwo.getChromosome()));
        System.out.println("Offspring1 " + Arrays.toString(newCouple.getThingOne().getChromosome()));
        System.out.println("Offspring2 " + Arrays.toString(newCouple.getThingTwo().getChromosome()));
    */
    }

    @Test
    public void breedSwapMultipleTimesTest(){
        for(int i=0; i< 1000; i++){
            breedSwapWorksCorrectly();
        }
    }
}
