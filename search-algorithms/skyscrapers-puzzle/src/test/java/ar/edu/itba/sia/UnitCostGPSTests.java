package ar.edu.itba.sia;

import ar.edu.itba.sia.game.SkyscrapersProblem;
import ar.edu.itba.sia.game.SkyscrapersPuzzle;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.MessageFormat;

public class UnitCostGPSTests {
/*
    private static GPSEngine bfsEngine;
    private static GPSEngine dfsEngine;
    private static GPSEngine iddfsEngine;
    private static GPSEngine aStarEngine;
    private static GPSEngine greedyEngine;

    @BeforeClass
    public static void setUp(){
        int leftViews3[] = {3, 0, 1};
        int topViews3[] = {3, 0, 1};
        int rightViews3[] = {1, 2, 0};
        int bottomViews3[] = {0, 2, 0};
        int mSwap3[][] = {
                {2, 3, 1},
                {3, 1, 2},
                {1, 2, 3}
        };

        int topViewsSwap4[] = {2, 1, 2, 2};
        int rightViewsSwap4[] = {3, 4, 1, 2};
        int leftViewsSwap4[] = {2, 1, 2, 2};
        int bottomViewsSwap4[] = {3, 4, 1, 2};
        int mSwap4[][] = {
                {1, 2, 3, 4},
                {2, 3, 4, 1},
                {3, 4, 1, 2},
                {4, 1, 2, 3}
        };

        int topViewsSwap5[] = {4, 2, 1, 2, 3};
        int rightViewsSwap5[] = {3, 4, 1, 2, 2};
        int leftViewsSwap5[] = {3, 2, 3, 2, 1};
        int bottomViewsSwap5[] = {1, 4, 3, 2, 2};
        int views[] = {0, 0, 0, 0, 0};
        int mSwap5[][] = {
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5, 1},
                {3, 4, 5, 1, 2},
                {4, 5, 1, 2, 3},
                {5, 1, 2, 3, 4}
        };

        int topViewsSwap9[] = {2, 1, 2, 3, 3, 3, 4, 2, 6};
        int rightViewsSwap9[] = {5, 6, 2, 3, 2, 3, 2, 3, 1};
        int leftViewsSwap9[] = {2, 1, 6, 3, 3, 2, 3, 4, 3};
        int bottomViewsSwap9[] = {3, 4, 3, 2, 2, 3, 3, 4, 1};
        int mSwap9[][] = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };

        SkyscrapersProblem problemSwap = new SkyscrapersProblem(5, topViewsSwap5, views, leftViewsSwap5, views,
                SkyscrapersPuzzle.getSwapRules(mSwap5), mSwap5);
        //SkyscrapersProblem problemSwap = new SkyscrapersProblem(4, topViewsSwap4, bottomViewsSwap4, leftViewsSwap4, rightViewsSwap4,
                //SkyscrapersPuzzle.getSwapRules(mSwap4), mSwap4);
        //SkyscrapersProblem problemFill = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, SkyscrapersPuzzle.getFillRules(m2), m2);

        bfsEngine = new GPSEngine(problemSwap, SearchStrategy.BFS, null);
        dfsEngine = new GPSEngine(problemSwap, SearchStrategy.DFS, null);
        iddfsEngine = new GPSEngine(problemSwap, SearchStrategy.IDDFS, null);
        aStarEngine = new GPSEngine(problemSwap, SearchStrategy.ASTAR, new AdmissibleHeuristic());
        greedyEngine = new GPSEngine(problemSwap, SearchStrategy.GREEDY, new AdmissibleHeuristic());

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

    @Test
    public void findSolution(){
        GenericTests.solutionFound(bfsEngine);
        GenericTests.solutionFound(dfsEngine);
        GenericTests.solutionFound(iddfsEngine);
        GenericTests.solutionFound(aStarEngine);
        GenericTests.solutionFound(greedyEngine);
    }

    private static void sameSolutions(GPSEngine engine1, GPSEngine engine2){
        GPSNode engine1SolutionNode = engine1.getSolutionNode();
        GPSNode engine2SolutionNode = engine2.getSolutionNode();
        assert engine1SolutionNode.getCost().equals(engine2SolutionNode.getCost()) :
                MessageFormat.format("{0} solution must have same cost as {1} solution. {0} cost: {2}, {1} cost: {3}",
                        engine1.getStrategy().name(), engine2.getStrategy().name(),
                        engine1SolutionNode.getCost(), engine2SolutionNode.getCost());
        assert engine1SolutionNode.getCost().equals(engine1SolutionNode.getDepth()) :
                MessageFormat.format("{0} solution must have same cost and depth. {0} cost: {1}, {0} depth: {2}",
                        engine1.getStrategy(),
                        engine1SolutionNode.getCost(), engine1SolutionNode.getDepth());
    }

    private static void hasNoSameSolution(GPSEngine optimalEngine, GPSEngine nonOptimalEngine){
        GPSNode optimalSolutionNode = optimalEngine.getSolutionNode();
        GPSNode nonOptimalSolutionNode = nonOptimalEngine.getSolutionNode();
        assert optimalSolutionNode.getCost() < nonOptimalSolutionNode.getCost() :
                MessageFormat.format("{0} solution must have less cost than {1} solution. {0} cost: {2}, {1} cost: {3}",
                        optimalEngine.getStrategy().name(), nonOptimalEngine.getStrategy(),
                        optimalSolutionNode.getCost(), nonOptimalSolutionNode.getCost());
    }


    @Test
    public void sameSolutionAStarVsBFS(){
        sameSolutions(aStarEngine,bfsEngine);
    }



    @Test
    public void sameSolutionAStarVsIDDFS() {
        sameSolutions(aStarEngine,iddfsEngine);
    }



    @Test
    public void noSameSolutionAStarvsGreedy(){
        hasNoSameSolution(aStarEngine, greedyEngine);

    }


    @Test
    public void noSameSolutionAStarVsDFS(){
        hasNoSameSolution(aStarEngine,dfsEngine);
    }


    @AfterClass
    public static void tearDown(){
        System.out.println("Unit cost OK");
    }
    */
}