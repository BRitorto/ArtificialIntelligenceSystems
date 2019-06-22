package ar.edu.itba.sia.Utils;

import ar.edu.itba.sia.Engine.Combinators.UniformCross;
import ar.edu.itba.sia.Engine.Conditioners.ContentConditioner;
import ar.edu.itba.sia.Engine.Conditioners.GenerationConditioner;
import ar.edu.itba.sia.Engine.Conditioners.OptimumConditioner;
import ar.edu.itba.sia.Engine.Conditioners.StructureConditioner;
import ar.edu.itba.sia.Engine.Combinators.AnnularCross;
import ar.edu.itba.sia.Engine.Combinators.DoublePointCross;
import ar.edu.itba.sia.Engine.Combinators.SinglePointCross;
import ar.edu.itba.sia.Engine.Items;
import ar.edu.itba.sia.Engine.Mutators.MultiGeneMutator;
import ar.edu.itba.sia.Engine.Mutators.OneGeneMutator;
import ar.edu.itba.sia.Engine.Replacers.KeepSomeAncestorsReplacer;
import ar.edu.itba.sia.Engine.Replacers.MixAllReplacer;
import ar.edu.itba.sia.Engine.Replacers.NewGenerationReplacer;
import ar.edu.itba.sia.Engine.Selectors.*;
import ar.edu.itba.sia.Generics.*;

public class ParameterFactories {
    /* package */ final static String ANNULAR = "AnnularCross";
    /* package */ final static String DOUBLEPOINT = "DoublePointCross";
    /* package */ final static String SINGLEPOINT = "SinglePointCross";
    /* package */ final static String UNIFORM = "UniformCross";
    /* package */ final static String UNIFORMONEGENE = "UniformOneGeneMutator";
    /* package */ final static String UNIFORMMULTIGENE = "UniformMultiGeneMutator";
    /* package */ final static String NONUNIFORMONEGENE = "NonUniformOneGeneMutator";
    /* package */ final static String NONUNIFORMMULTIGENE = "NonUniformMultiGeneMutator";

    /* package */ final static String BOLTZMANNPRTOURNAMENT = "BoltzmannPRTournamentSelection";
    /* package */ final static String BOLTZMANNROULETTE = "BoltzmannRouletteSelection";
    /* package */ final static String DETERMINISTICTORUNAMENT = "DeterministicTournamentSelection";
    /* package */ final static String ELITE= "EliteSelection";
    /* package */ final static String PROBABILISTICTOURNAMENT = "ProbabilisticTournamentSelection";
    /* package */ final static String RANKING = "RankingSelection";
    /* package */ final static String ROULETTE = "RouletteSelection";
    /* package */ final static String UNIVERSAL = "UniversalSelection";

    /* package */ final static String KEEPSOMEANCESTORS = "KeepSomeAncestorsReplacer";
    /* package */ final static String MIXALL = "MixAllReplacer";
    /* package */ final static String NEWGENERATION = "NewGenerationReplacer";

    /* package */ final static String CONTENT = "ContentConditioner";
    /* package */ final static String GENERATION = "GenerationConditioner";
    /* package */ final static String OPTIMUM = "OptimumConditioner";
    /* package */ final static String STRUCTURE = "StructureConditioner";

    final static double MAX_MUTATION_PROB = 0.8;
    final static double MIN_MUTATION_PROB = 0.2;
    final static double UNIFORM_MUTATION_PROB = 0.2;
    final static double MUTATION_STEP = 0.006;

    public static Combinator createCombinator(String crossover, double probability) {

        if (probability > 1) {
            throw new IllegalArgumentException("Probability can't be greater than 1!");
        }
        switch (crossover) {
            case ANNULAR:
                return new AnnularCross(probability);
            case DOUBLEPOINT:
                return new DoublePointCross(probability);
            case SINGLEPOINT:
                return new SinglePointCross(probability);
            case UNIFORM:
                return new UniformCross(probability);
            default:
                return null;
        }
    }

    public static Mutator createMutator(String mutator, Items itemPool) {
        switch (mutator) {
            case UNIFORMONEGENE:
                return new OneGeneMutator((individual, generation)-> {
                    return UNIFORM_MUTATION_PROB;
                }, itemPool);
            case UNIFORMMULTIGENE:
                return new MultiGeneMutator((individual, generation)-> {
                    return UNIFORM_MUTATION_PROB;
                }, itemPool);
            case NONUNIFORMONEGENE:
                return new OneGeneMutator((individual, generation)-> {
                    if(generation%10 == 0){
                        long aux = generation/10;
                        return Math.max(MAX_MUTATION_PROB-aux*MUTATION_STEP,MIN_MUTATION_PROB);
                    }
                    return Math.max(MAX_MUTATION_PROB-Math.floor(generation/10)*MUTATION_STEP,MIN_MUTATION_PROB);
                }, itemPool);
            case NONUNIFORMMULTIGENE:
                return new OneGeneMutator((individual, generation)-> {
                    if(generation%10 == 0){
                        long aux = generation/10;
                        return Math.max(MAX_MUTATION_PROB-aux*MUTATION_STEP,MIN_MUTATION_PROB);
                    }
                    return Math.max(MAX_MUTATION_PROB-Math.floor(generation/10)*MUTATION_STEP,MIN_MUTATION_PROB);
                }, itemPool);
            default:
                return null;
        }
    }

    public static Selector createSelector(String selector) {
        switch (selector) {
            case BOLTZMANNPRTOURNAMENT:
                return new BoltzmannPRTournamentSelection();
            case BOLTZMANNROULETTE:
                return new BoltzmannRouletteSelection();
            case DETERMINISTICTORUNAMENT:
                return new DeterministicTournamentSelection();
            case ELITE:
                return new EliteSelection();
            case PROBABILISTICTOURNAMENT:
                return new ProbabilisticTournamentSelection();
            case RANKING:
                return new RankingSelection();
            case ROULETTE:
                return new RouletteSelection();
            case UNIVERSAL:
                return new UniversalSelection();
            default:
                return null;
        }
    }

    public static Replacer createReplacer(String replacer, Selector selector1, Selector selector2, Selector selector3,
                                          Selector selector4, double ratioA, double ratioB) {
        switch (replacer) {
            case KEEPSOMEANCESTORS:
                return new KeepSomeAncestorsReplacer(selector1, selector2, selector3, selector4, ratioA, ratioB);
            case MIXALL:
                return new MixAllReplacer(selector1, selector2, selector3, selector4, ratioA, ratioB);
            case NEWGENERATION:
                return new NewGenerationReplacer();
            default:
                return null;
        }
    }

    public static Conditioner createConditioner(String conditioner, Long num) {
        switch (conditioner) {
            case CONTENT:
                return new ContentConditioner(num);
            case GENERATION:
                return new GenerationConditioner(num);
            case OPTIMUM:
                return new OptimumConditioner(num);
            case STRUCTURE:
                return new StructureConditioner(num);
            default:
                return null;
        }
    }
}
