package ar.edu.itba.sia.game;

import ar.edu.itba.sia.EngineFactory;
import ar.edu.itba.sia.GPSEngine;
import ar.edu.itba.sia.SearchStrategy;
import ar.edu.itba.sia.game.rules.SkyscrapersSwapRowRule;

import java.util.LinkedList;

public class SkyscrapersPuzzle {

    public static void main(String args[]) {
        int leftViews[] = {2,2, 1};
        int topViews[] = {2, 2, 1};
        int rightViews[] = {1, 2, 3};
        int bottomViews[] = {1, 2, 3};
        int m[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

//        int leftViews[] = {2,3,2,1};
//        int topViews[] = {2,1,3,2};
//        int rightViews[] = {2,1,2,3};
//        int bottomViews[]={1,3,2,3};
//        int elseViews[] = {0, 0, 0,0};
//        int m[][] = {{0, 0, 0,0}, {0,0,0, 0}, {0,0, 0, 0},{0,0,0,0}};

        //int leftViews[] = {3, 2, 3, 2, 1};
        //int topViews[] = {4, 2, 1, 2, 3};
        //int rightViews[] = {3, 4, 1, 2, 2};
        //int bottomViews[] = {1, 4, 3, 2, 2};
        //int m[][] = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

        LinkedList rules = new LinkedList<SkyscrapersSwapRowRule>();
        int size = m.length;


        SkyscrapersProblem problem = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, rules);
        EngineFactory factory = new EngineFactory();
        GPSEngine engine = factory.buildEngine(problem, SearchStrategy.BFS, null, 0);

        //SkyscrapersState state = (SkyscrapersState) problem.getRules().get(0).apply(problem.getInitState()).get();

//        int count = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 1; k < 4; k++) {
//                    if (problem.getRules().get(count).apply(state).isPresent()) {
//                        state = (SkyscrapersState) problem.getRules().get(count).apply(state).get();
//                        System.out.println(state.getRepresentation());
//                    }
//                    count++;
//                    System.out.println(count);
//                }
//            }
//        }

        engine.findSolution();
    }



// inicializar rules y problem
    // depenede de si es swap o put
    // return new engine con incluido la search strategy
}
