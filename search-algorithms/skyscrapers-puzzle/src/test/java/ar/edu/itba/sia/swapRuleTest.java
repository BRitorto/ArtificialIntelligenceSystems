package ar.edu.itba.sia;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.SkyscrapersProblem;
import ar.edu.itba.sia.game.SkyscrapersPuzzle;
import ar.edu.itba.sia.game.SkyscrapersState;
import ar.edu.itba.sia.game.UI.Helpers;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import ar.edu.itba.sia.game.rules.SkyscrapersSwapRule;
import ar.edu.itba.sia.gps.EngineFactory;
import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Rule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class swapRuleTest {
    @Before
    public void setup() {

    }

    @Test
    public void swapRuleWorksTest() {
        int topViews[] = {2,1,3,2};
        int leftViews[] = {0,3,2,1};
        int rightViews[] = {2,1,2,3};
        int bottomViews[]={0,0,0,0};
        int m[][] = {{1,2,3,4}, {2,3,4,1}, {3,4,1,2},{4,1,2,3}};

        AdmissibleHeuristic ad = new AdmissibleHeuristic();
        Board board = new Board(m.length, topViews, bottomViews, leftViews, rightViews, m);
        SkyscrapersProblem problem = new SkyscrapersProblem(board, SkyscrapersPuzzle.getSwapRules(m));
        GPSEngine engine = EngineFactory.buildEngine(problem, SearchStrategy.GREEDY, ad, 0);
        engine.findSolution();

        if (engine.getSolutionNode() == null)
            System.out.println("No hay solucion");
        else
            System.out.println(engine.getSolutionNode().getState().getRepresentation());
    }

    @Test
    public void createSwapBoardWorksTest(){
        int[][] dim3 = {{1, 2, 3}, {2, 3, 1}, {3, 1 , 2}};
        int[][] dim4 = {{1, 2, 3, 4}, {2, 3, 4, 1}, {3, 4, 1 , 2}, {4, 1, 2, 3}};
        int[][] dim5 = {{1, 2, 3, 4, 5}, {2, 3, 4, 5, 1}, {3, 4, 5, 1 , 2}, {4, 5, 1, 2, 3}, {5, 1, 2, 3, 4}};

        assertEquals(dim3, Helpers.createSwapMatrix(3));
        assertEquals(dim4, Helpers.createSwapMatrix(4));
        assertEquals(dim5, Helpers.createSwapMatrix(5));
    }
}
