package ar.edu.itba.sia.game;

import ar.edu.itba.sia.EngineFactory;
import ar.edu.itba.sia.GPSEngine;
import ar.edu.itba.sia.SearchStrategy;

import java.awt.*;
import java.util.LinkedList;

public class SkyscrapersPuzzle {

    public static void main(String args[]) {
//        int leftViews[] = {0,0,0};
//        int topViews[] = {0, 0, 0};
//        int rightViews[]={0,0,0};
//        int bottomViews[]={1,2,2};
//        int elseViews[] = {0, 0, 0};
//        int m[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

//        int leftViews[] = {2,3,2,1};
//        int topViews[] = {2,1,3,2};
//        int rightViews[] = {2,1,2,3};
//        int bottomViews[]={1,3,2,3};
//        int elseViews[] = {0, 0, 0,0};
//        int m[][] = {{0, 0, 0,0}, {0,0,0, 0}, {0,0, 0, 0},{0,0,0,0}};

        int leftViews[] = {3, 2, 3, 2, 1};
        int topViews[] = {4, 2, 1, 2, 3};
        int rightViews[] = {3, 4, 1, 2, 2};
        int bottomViews[] = {1, 4, 3, 2, 2};
        int m[][] = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

        LinkedList rules = new LinkedList<SkyscrapersFillRule>();
        Board b = new Board(5, topViews, bottomViews, leftViews, rightViews, m);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                for (int k = 1; k<6; k++){
                    SkyscrapersFillRule rule = new SkyscrapersFillRule(k, i, j);
                    rules.add(rule);
                }
            }
        }

        SkyscrapersProblem problem = new SkyscrapersProblem(5, topViews, bottomViews, leftViews, rightViews, rules);
        EngineFactory factory = new EngineFactory();
        GPSEngine engine = factory.buildEngine(problem, SearchStrategy.BFS, null, 0);
        engine.findSolution();
    }



// inicializar rules y problem
    // depenede de si es swap o put
    // return new engine con incluido la search strategy
}
