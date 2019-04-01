package ar.edu.itba.sia;

import ar.edu.itba.sia.game.BoardValidator;
import ar.edu.itba.sia.game.SkyscrapersProblem;
import ar.edu.itba.sia.game.SkyscrapersPuzzle;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import ar.edu.itba.sia.game.heuristics.NonAdmissibleHeuristic;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.MessageFormat;

public class HeuristicGPSTests {
/*
    private static GPSEngine betterHeuristicEngine;
    private static GPSEngine worseHeuristicEngine;


    @BeforeClass
    public static void setUp(){

        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,2,3};
        int bottomViews[]={1,2,3};
        int m[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        SkyscrapersProblem betterHeuristicProblem = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, SkyscrapersPuzzle.getSwapRules(m), m);
        SkyscrapersProblem worseHeuristicProblem = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, SkyscrapersPuzzle.getSwapRules(m), m);
        betterHeuristicEngine = new GPSEngine(betterHeuristicProblem , SearchStrategy.ASTAR, new AdmissibleHeuristic());
        worseHeuristicEngine = new GPSEngine(worseHeuristicProblem, SearchStrategy.ASTAR, new NonAdmissibleHeuristic());

        System.out.println("Finding solution for the better heuristic");
        betterHeuristicEngine.findSolution();
        System.out.println("Finding solution for the worse heuristic");
        worseHeuristicEngine.findSolution();
        System.out.println("Heuristic engine ran, running the tests");

    }

    @Test
    public void differHDifferExplosionCount(){
        assert betterHeuristicEngine.getExplosionCounter() < worseHeuristicEngine.getExplosionCounter() :
                MessageFormat.format("If differ H, better H has less explosion count than worse H. " +
                                "Better H explosion count: {0}, Worse explosion count: {1}",
                        betterHeuristicEngine.getExplosionCounter(), worseHeuristicEngine.getExplosionCounter());
    }

    @Test
    public void differHDifferExploredStatesCount(){
        assert betterHeuristicEngine.getBestCosts().size() < worseHeuristicEngine.getBestCosts().size() :
                MessageFormat.format("If differ H, Better H has less explored states counter than worse H. " +
                                "Better H explored states count: {0}, worse H explored count: {1}",
                        betterHeuristicEngine.getBestCosts().size(), worseHeuristicEngine.getBestCosts().size());
    }

    @Test
    public void differHSameCostSolution(){
        assert betterHeuristicEngine.getSolutionNode().getCost().equals(worseHeuristicEngine.getSolutionNode().getCost()) :
                MessageFormat.format("If differ H, both H solution have same cost. " +
                                "H1 explosion count: {0}, H2 explosion count: {1}",
                        betterHeuristicEngine.getSolutionNode().getCost(), worseHeuristicEngine.getSolutionNode().getCost());
    }

    @Test
    public void generalTests(){
        GenericTests.solutionFound(betterHeuristicEngine);
        GenericTests.solutionFound(worseHeuristicEngine);
    }

    @AfterClass
    public static void tearDown(){
        System.out.println("Heuristic OK");
    }
    */
}