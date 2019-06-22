package ar.edu.itba.sia.Engine.Crossover;

import ar.edu.itba.sia.Engine.Combinators.SinglePointCross;
import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Game.Profession;
import ar.edu.itba.sia.Generics.Couple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class CrossTest {

    private Profession prof;

    @Before
    public void setUp(){
        prof = Profession.valueOf("ARCHER1");
    }
    @Test
    public void pickCouplesGeneratesAValidList(){
        List<Couple<GameCharacter>> generatedCouples;
        List<GameCharacter> gameCharacters = new LinkedList<>();
        int startingIndex = 1;
        for(int i = 0; i<1000; i++){
            gameCharacters.add(CharacterGenerator.createCharacter(prof, startingIndex));
            startingIndex+=5;
        }
        SinglePointCross sp = new SinglePointCross(0.5);
        generatedCouples = sp.pickCouples(gameCharacters, 100);

        Assert.assertNotEquals(0, generatedCouples.size());
        Assert.assertEquals(100, generatedCouples.size()); //Cut earlier by generation

        for(Couple<GameCharacter> couple : generatedCouples){
            Assert.assertNotNull(couple);
        }

    }
}
