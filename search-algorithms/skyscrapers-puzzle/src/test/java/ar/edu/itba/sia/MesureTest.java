package ar.edu.itba.sia;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.SkyscrapersProblem;
import ar.edu.itba.sia.game.SkyscrapersPuzzle;
import ar.edu.itba.sia.game.UI.Helpers;
import ar.edu.itba.sia.game.UI.InteractiveGame;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import ar.edu.itba.sia.game.heuristics.NonAdmissibleHeuristic;
import ar.edu.itba.sia.gps.EngineFactory;
import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.SearchStrategy;
import org.junit.Before;
import org.junit.Test;

public class MesureTest {


    @Before
    public void setup() {

    }

    @Test
    public void swapRuleWorksTest() {
        //3x3
        int topViews[] = {2,2,1};
        int leftViews[] = {3,1,2};
        int rightViews[] = {0,2,2};
        int bottomViews[] = {0,0,3};
        int m[][] = {{1,2,3},{1,2,3},{1,2,3}};

//        int topViews[] = {2,1,3};
//        int leftViews[] = {0,0,3};
//        int rightViews[] = {2,2,0};
//        int bottomViews[] = {0,2,1};
//        int m[][] = {{1,2,3},{2,3,1},{3,1,2}};

        //anterior

//        int topViews[] = {2,2,1};
//        int leftViews[] = {2,2,0};
//        int rightViews[] = {1,2,3};
//        int bottomViews[] = {1,0,3};
//        int m[][] = {{1,2,3},{2,3,1},{3,1,2}};


//        int topViews[] = {0,1,0};
//        int leftViews[] = {2,2,0};
//        int rightViews[] = {0,1,3};
//        int bottomViews[] = {1,2,2};
//        int m[][] = {{1,2,3},{2,3,1},{3,1,2}};
        //int m[][] = {{1,2,3},{2,3,1},{2,1,3}};

        //4x4
//
//        int topViews[] = {3 ,2,2,1};
//        int leftViews[] = {4,2,1,2};
//        int rightViews[] = {1,2,4,2};
//        int bottomViews[] = {2,3,1,3};
//        int m[][] = {{1, 2, 3, 4}, {2, 3, 4, 1}, {3, 4, 1, 2}, {4, 1, 2, 3}};


//        int topViews[] = {2,2,2,4};
//        int leftViews[] = {2,1,2,4};
//        int rightViews[] = {3,3,2,1};
//        int bottomViews[] = {3,3,2,1};
       //int m[][] = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};

//        int topViews[] = {0,0,0,0};
//        int leftViews[] = {0,0,0,2};
//        int rightViews[] = {0,0,1,0};
//        int bottomViews[] = {0,2,0,0};
//        int m[][] = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};


        //viejo
//
//        int topViews[] = {3,1,2,0};
//        int leftViews[] = {0,2,4,0};
//        int rightViews[] = {2,0,1,4};
//        int bottomViews[] = {1,0,3,2};
//        int m[][] = {{3,4,1,2}, {1,3,2,4}, {2,1,4,3}, {4,2,3,1}};
//        int m[][] = {{1, 2, 3, 4}, {2, 3, 4, 1}, {3, 4, 1, 2}, {4, 1, 2, 3}};
//        int m[][] = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};

        //5x5
        //vieja
        //se rompe
//        int topViews[] = {5,4,3,2,1};
//        int leftViews[] = {5,0,3,2,0};
//        int rightViews[] = {1,0,2,2,0};
//        int bottomViews[] = {1,0,0,2,2};
//        int m[][] = {{1,4,5,3,2}, {2,5,1,4,3}, {4,2,3,1,5}, {5,3,4,2,1},{3,1,2,5,4}};


        AdmissibleHeuristic ad = new AdmissibleHeuristic();
        NonAdmissibleHeuristic nad= new NonAdmissibleHeuristic();
        Board board = new Board(m.length, topViews, bottomViews, leftViews, rightViews, m);
        System.out.println("CONFLICTOs:"+board.getBoardValidator().cantConflicts(board));
        SkyscrapersProblem problem = new SkyscrapersProblem(board, SkyscrapersPuzzle.getSwapRules(m));
        GPSEngine engine = EngineFactory.buildEngine(problem, SearchStrategy.BFS ,null, 0);

        long delta;
        long start = System.nanoTime();

        engine.findSolution();
        delta = System.nanoTime() - start;

        if (engine.getSolutionNode() == null)
            System.out.println("No hay solucion");
        else {
            System.out.println("Game ended, winning board: ");
            System.out.println(engine.getSolutionNode().getState().getRepresentation());
            System.out.println("Depth of the solution: " + engine.getSolutionNode().getDepth());
            System.out.println("Total solution cost: " + engine.getSolutionNode().getCost());
            System.out.println("Qty of exploded nodes: " + engine.getExplosionCounter());
            System.out.println("Analized states # : " + engine.getBestCosts().size());
            System.out.println("# Frontier Nodes " + engine.getOpen().size());
            System.out.println("Time expended " + delta + " ns");
        }
    }

    @Test
    public void createNewMatrixTest(){
        int[][] hola = Helpers.createSwapMatrix(4);
        for (int i = 0; i< 4; i++){
            for(int j = 0; j<4; j++){
                System.out.printf(hola[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}


