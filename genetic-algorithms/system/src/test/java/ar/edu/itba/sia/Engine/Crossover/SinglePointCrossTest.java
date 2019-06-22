package ar.edu.itba.sia.Engine.Crossover;

import ar.edu.itba.sia.Engine.Combinators.SinglePointCross;
import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Game.Profession;
import ar.edu.itba.sia.Generics.Couple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SinglePointCrossTest {
    private GameCharacter thingOne;
    private GameCharacter thingTwo;
    private Profession prof;

    @Before
    public void setUp(){
        prof = Profession.valueOf("ARCHER1");
        thingOne = CharacterGenerator.createCharacter(prof, 1);
        thingTwo = CharacterGenerator.createCharacter(prof, 6);
    }

    private void assertChromosomesNotNull(GameCharacter thing){
        for(Object chromosome : thing.getChromosome()) {
            Assert.assertNotNull(chromosome);
        }
    }

    @Test
    public void breedOffspringDifferentThanParentsTest(){
        // Mutate thingOne and Two
        assertChromosomesNotNull(thingOne);
        assertChromosomesNotNull(thingTwo);

        SinglePointCross sp = new SinglePointCross(0.5);

        Couple<GameCharacter> couple = new Couple<>(thingOne, thingTwo);
        couple = sp.breed(couple);

        Assert.assertNotEquals(thingOne, couple.getThingOne());
        Assert.assertNotEquals(thingTwo, couple.getThingTwo());

        Assert.assertNotEquals(couple.getThingOne(), couple.getThingTwo());

        /* Debugging purpose only
        System.out.println("Random Index: " + sp.getRandomIndex());
        System.out.println("Parent1 " + Arrays.toString(thingOne.getChromosome()));
        System.out.println("Parent2 " + Arrays.toString(thingTwo.getChromosome()));
        System.out.println("Offspring1 " + Arrays.toString(couple.getThingOne().getChromosome()));
        System.out.println("Offspring2 " + Arrays.toString(couple.getThingTwo().getChromosome()));
        */
    }

    @Test
    public void breedSwapWorksCorrectlyTest(){
        assertChromosomesNotNull(thingOne);
        assertChromosomesNotNull(thingTwo);

        SinglePointCross sp = new SinglePointCross(0.5);
        Couple<GameCharacter> couple = new Couple<>(thingOne, thingTwo);

        for(int i = 0; i< sp.getRandomIndex()-1; i++){
            Assert.assertEquals(thingOne.getChromosome()[i], couple.getThingOne().getChromosome()[i]);
            Assert.assertEquals(thingTwo.getChromosome()[i], couple.getThingTwo().getChromosome()[i]);
        }

        for(int i = sp.getRandomIndex()-1; i>=0 && i< thingOne.getChromosome().length; i++){
            Assert.assertEquals(thingOne.getChromosome()[i], couple.getThingTwo().getChromosome()[i]);
            Assert.assertEquals(thingTwo.getChromosome()[i], couple.getThingOne().getChromosome()[i]);
        }


    }

    @Test
    public void SwapWorksCorrectlyMultipleTimesTest(){
        for(int i = 0; i< 10000; i++){
            breedSwapWorksCorrectlyTest();
        }
    }

}
