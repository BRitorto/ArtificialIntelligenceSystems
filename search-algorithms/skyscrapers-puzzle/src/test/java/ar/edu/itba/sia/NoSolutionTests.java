package ar.edu.itba.sia;


import ar.edu.itba.sia.game.BoardValidator;
import ar.edu.itba.sia.game.SkyscrapersProblem;
import ar.edu.itba.sia.game.SkyscrapersPuzzle;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Objects;

public class NoSolutionTests {

    private static GPSEngine bfsEngine;
    private static GPSEngine dfsEngine;
    private static GPSEngine iddfsEngine;
    private static GPSEngine aStarEngine;
    private static GPSEngine greedyEngine;

    @BeforeClass
    public static void setUp(){
        int leftViews[] = {1,2,2};
        int topViews[] = {1,2,2};
        int rightViews[] = {3,2,1};
        int bottomViews[] = {3,2,1};
        int m[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        SkyscrapersProblem problem = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, SkyscrapersPuzzle.getFillRules(m), m);

        bfsEngine = new GPSEngine(problem, SearchStrategy.BFS, null);
        dfsEngine = new GPSEngine(problem, SearchStrategy.DFS, null);
        iddfsEngine = new GPSEngine(problem, SearchStrategy.IDDFS, null);
        aStarEngine = new GPSEngine(problem, SearchStrategy.ASTAR, new AdmissibleHeuristic());
        greedyEngine = new GPSEngine(problem, SearchStrategy.GREEDY, new AdmissibleHeuristic());

        System.out.println("Finding bfs solution");
        bfsEngine.findSolution();
        System.out.println("Finding dfs solution");
        dfsEngine.findSolution();
        System.out.println("Finding iddfs solution");
        iddfsEngine.findSolution();
        System.out.println("Finding aStar solution");
        aStarEngine.findSolution();
        System.out.println("Finding greedy solution");
        greedyEngine.findSolution();
        System.out.println("All engine ran, running the tests");
    }


    private static void noSolutionFound(GPSEngine engine){
        assert engine.isFinished() : String.format("After findSolution, the finished flag was not set, with %s strategy", engine.getStrategy().name());
        assert engine.getOpen().isEmpty() : String.format("After findSolution with a no solution problem, the open queue was not empty, with %s strategy", engine.getStrategy().name());
        assert engine.isFailed() : String.format("After findSolution with a no solution problem, the finished flag was not set, with %s strategy", engine.getStrategy().name());
        assert Objects.isNull(engine.getSolutionNode()) : "After findSolution, engine ended but solution node is not null";
    }

    private static void noSolutionSameStatesCount(GPSEngine engine1, GPSEngine engine2){
        assert engine1.getBestCosts().size() == engine2.getBestCosts().size() :
                MessageFormat.format("After findSolution with no solution, {0} and {1} should have the same explored states count. " +
                                "{0} explored states count: {2}, {1} explored states count: {3}", engine1.getStrategy().name(), engine2.getStrategy().name(),
                        engine1.getBestCosts().size(), engine2.getBestCosts().size());
    }

    private static void noSolutionSameExplosionCount(GPSEngine engine1, GPSEngine engine2){
        assert engine1.getExplosionCounter() == engine2.getExplosionCounter() :
                MessageFormat.format("After findSolution with no solution, {0} and {1} should have the same explosion count. " +
                                "{0} explosion count: {2}, {1} explosion count: {3}", engine1.getStrategy().name(), engine2.getStrategy().name(),
                        engine1.getExplosionCounter(), engine2.getExplosionCounter());
    }

    private static void noSolutionDifferExplosionCount(GPSEngine engineLesser, GPSEngine engineGreater){
        assert engineLesser.getExplosionCounter() <= engineGreater.getExplosionCounter() :
                MessageFormat.format("After findSolution with no solution, {0} should have greater or equal explosion counter than {1}. " +
                                "{0} explosion count: {2}, {1} explosion count: {3}", engineGreater.getStrategy().name(), engineLesser.getStrategy().name(),
                        engineGreater.getExplosionCounter(), engineLesser.getExplosionCounter());
    }

    private static void noSolutionStrictDifferExplosionCount(GPSEngine engineLesser, GPSEngine engineGreater){
        assert engineLesser.getExplosionCounter() < engineGreater.getExplosionCounter() :
                MessageFormat.format("After findSolution with no solution, {0} should have greater explosion counter than {1}. " +
                                "{0} explosion count: {2}, {1} explosion count: {3}", engineGreater.getStrategy().name(), engineLesser.getStrategy().name(),
                        engineGreater.getExplosionCounter(), engineLesser.getExplosionCounter());
    }

    @Test
    public void noSolutionFoundAStar(){
        noSolutionFound(aStarEngine);
    }

    @Test
    public void noSolutionFoundBFS(){
        noSolutionFound(bfsEngine);
    }

    @Test
    public void noSolutionFoundDFS(){
        noSolutionFound(dfsEngine);
    }

    @Test
    public void noSolutionFoundGreedy(){
        noSolutionFound(greedyEngine);
    }

    @Test
    public void noSolutionFoundIDDFS(){
        noSolutionFound(iddfsEngine);
    }

    @Test
    public void noSolutionAStarVsDFS(){
        noSolutionSameExplosionCount(aStarEngine,dfsEngine);
        noSolutionSameStatesCount(aStarEngine,dfsEngine);
    }

    @Test
    public void noSolutionAStarVsBFS(){
        noSolutionSameExplosionCount(aStarEngine,bfsEngine);
        noSolutionSameStatesCount(aStarEngine,bfsEngine);
    }

    @Test
    public void noSolutionAStarVsGreedy(){
        noSolutionDifferExplosionCount(aStarEngine, greedyEngine);
        noSolutionSameStatesCount(aStarEngine,greedyEngine);
    }

    @Test
    public void noSolutionDFSVsIDDFS(){
        noSolutionStrictDifferExplosionCount(dfsEngine, iddfsEngine);
        noSolutionSameStatesCount(dfsEngine,iddfsEngine);

    }

    @AfterClass
    public static void tearDown(){
        System.out.println("Not solution OK");
    }
}
