package ar.edu.itba.sia.Engine.Crossover;

import ar.edu.itba.sia.Engine.Combinators.AnnularCross;
import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Game.Profession;
import ar.edu.itba.sia.Generics.Couple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnnularCrossTest {
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
        AnnularCross ac = new AnnularCross(0.5);

        Couple<GameCharacter> newCouple;
        newCouple = ac.breed(new Couple<>(thingOne, thingTwo));

        Assert.assertNotNull(newCouple);
        Assert.assertNotNull(newCouple.getThingOne());
        Assert.assertNotNull(newCouple.getThingTwo());
    }

    @Test
    public void breedSwapWorksCorrectly(){
        AnnularCross ac = new AnnularCross(0.5);

        Couple<GameCharacter> newCouple = ac.breed(new Couple<>(thingOne, thingTwo));

//        System.out.println("From Index: " + ac.getFromIndex());
//        System.out.println("To Index: " + ac.getToIndex());
//        System.out.println("Length: " + ac.getLength());
//        System.out.println("Chromosome length: " + ac.getChromosomeLength());
//
//        System.out.println("Parent1 " + Arrays.toString(thingOne.getChromosome()));
//        System.out.println("Parent2 " + Arrays.toString(thingTwo.getChromosome()));
//        System.out.println("Offspring1 " + Arrays.toString(newCouple.getThingOne().getChromosome()));
//        System.out.println("Offspring2 " + Arrays.toString(newCouple.getThingTwo().getChromosome()));

        int chromosomeLength = ac.getChromosomeLength();

        if(ac.getFromIndex()<ac.getToIndex()) {
            for (int i = 0; i < ac.getFromIndex() - 1; i++) {
                Assert.assertEquals(thingOne.getChromosome()[i], newCouple.getThingOne().getChromosome()[i]);
                Assert.assertEquals(thingTwo.getChromosome()[i], newCouple.getThingTwo().getChromosome()[i]);
            }
        }

        for(int i = ac.getFromIndex()-1,j=0; j<= ac.getLength() ; i++,j++){
            Assert.assertEquals(thingOne.getChromosome()[i%chromosomeLength], newCouple.getThingTwo().getChromosome()[i%chromosomeLength]);
            Assert.assertEquals(thingTwo.getChromosome()[i%chromosomeLength], newCouple.getThingOne().getChromosome()[i%chromosomeLength]);
        }

        int auxEnd = ac.getFromIndex()<ac.getToIndex()?newCouple.getThingTwo().getChromosome().length:ac.getFromIndex()-1;
        for(int i = ac.getToIndex(); i < auxEnd; i++){
            Assert.assertEquals(thingOne.getChromosome()[i], newCouple.getThingOne().getChromosome()[i]);
            Assert.assertEquals(thingTwo.getChromosome()[i], newCouple.getThingTwo().getChromosome()[i]);
        }

    }

    @Test
    public void breedSwapMultipleTimesTest(){
        for(int i=0; i< 1000; i++){
            breedSwapWorksCorrectly();
        }
    }
}
