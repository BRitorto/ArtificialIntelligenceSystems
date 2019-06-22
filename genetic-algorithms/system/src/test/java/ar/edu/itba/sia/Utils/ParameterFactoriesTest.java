package ar.edu.itba.sia.Utils;


import ar.edu.itba.sia.Engine.Conditioners.ContentConditioner;
import ar.edu.itba.sia.Engine.Conditioners.GenerationConditioner;
import ar.edu.itba.sia.Engine.Conditioners.OptimumConditioner;
import ar.edu.itba.sia.Engine.Conditioners.StructureConditioner;
import ar.edu.itba.sia.Engine.Combinators.AnnularCross;
import ar.edu.itba.sia.Engine.Combinators.DoublePointCross;
import ar.edu.itba.sia.Engine.Combinators.SinglePointCross;
import ar.edu.itba.sia.Engine.Replacers.KeepSomeAncestorsReplacer;
import ar.edu.itba.sia.Engine.Replacers.MixAllReplacer;
import ar.edu.itba.sia.Engine.Replacers.NewGenerationReplacer;
import ar.edu.itba.sia.Engine.Selectors.*;
import ar.edu.itba.sia.Generics.*;
import org.junit.Assert;
import org.junit.Test;

public class ParameterFactoriesTest {

    private static final double prob = 0.1;
    @Test
    public void combinatorNamesTest(){
        Combinator annular = new AnnularCross(prob);
        Combinator singlePoint = new SinglePointCross(prob);
        Combinator doublePoint = new DoublePointCross(prob);

        Assert.assertEquals(ParameterFactories.ANNULAR, annular.getClassName());
        Assert.assertEquals(ParameterFactories.SINGLEPOINT, singlePoint.getClassName());
        Assert.assertEquals(ParameterFactories.DOUBLEPOINT, doublePoint.getClassName());
    }

    // This test is no longer correct since Uniform and NonUniform mutators are taken into account
//    @Test
//    public void mutatorNamesTest(){
//        Mutator oneGene = new OneGeneMutator(null, null);
//        Mutator multiGene = new MultiGeneMutator(null, null);
//
//        Assert.assertEquals(ParameterFactories.UNIFORMONEGENE, oneGene.getClassName());
//        Assert.assertEquals(ParameterFactories.UNIFORMMULTIGENE, multiGene.getClassName());
//
//    }

    @Test
    public void selectorNamesTest(){
        Selector boltzmann = new BoltzmannPRTournamentSelection();
        Selector detTorun = new DeterministicTournamentSelection();
        Selector elite = new EliteSelection();
        Selector probTourn = new ProbabilisticTournamentSelection();
        Selector roulette = new RouletteSelection();
        Selector universal = new UniversalSelection();

        Assert.assertEquals(ParameterFactories.BOLTZMANNPRTOURNAMENT, boltzmann.getClassName());
        Assert.assertEquals(ParameterFactories.DETERMINISTICTORUNAMENT, detTorun.getClassName());
        Assert.assertEquals(ParameterFactories.ELITE, elite.getClassName());
        Assert.assertEquals(ParameterFactories.PROBABILISTICTOURNAMENT, probTourn.getClassName());
        Assert.assertEquals(ParameterFactories.ROULETTE, roulette.getClassName());
        Assert.assertEquals(ParameterFactories.UNIVERSAL, universal.getClassName());
    }

    @Test
    public void replacerNamesTest(){
        Replacer keepSome = new KeepSomeAncestorsReplacer(null, null, null, null, 0,0);
        Replacer mixAll = new MixAllReplacer(null, null, null, null, 0, 0);
        Replacer newGen = new NewGenerationReplacer();

        Assert.assertEquals(ParameterFactories.KEEPSOMEANCESTORS, keepSome.getClassName());
        Assert.assertEquals(ParameterFactories.MIXALL, mixAll.getClassName());
        Assert.assertEquals(ParameterFactories.NEWGENERATION, newGen.getClassName());
    }

    @Test
    public void conditionerTest(){
        Conditioner content = new ContentConditioner(2);
        Conditioner gen = new GenerationConditioner(2);
        Conditioner opt = new OptimumConditioner(2);
        Conditioner struct =  new StructureConditioner(2);

        Assert.assertEquals(ParameterFactories.CONTENT, content.getClassName());
        Assert.assertEquals(ParameterFactories.GENERATION, gen.getClassName());
        Assert.assertEquals(ParameterFactories.OPTIMUM, opt.getClassName());
        Assert.assertEquals(ParameterFactories.STRUCTURE, struct.getClassName());
    }
}
