package ar.edu.itba.sia.game;

import ar.edu.itba.sia.EngineFactory;
import ar.edu.itba.sia.GPSEngine;
import ar.edu.itba.sia.SearchStrategy;
import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.game.rules.SkyscrapersFillRule;
import ar.edu.itba.sia.game.rules.SkyscrapersSwapColRule;
import ar.edu.itba.sia.game.rules.SkyscrapersSwapRowRule;

import java.util.LinkedList;
import java.util.List;

public class SkyscrapersPuzzle {

    public static void main(String args[]) {
//        int leftViews[] = {1,0,2};
//        int topViews[] = {0,0,3};
//        int rightViews[] = {3,2,1};
//        int bottomViews[] = {0,2,1};
//        int m[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        int leftViews[] = {3,0,1};
        int topViews[] = {3,0,1};
        int rightViews[] = {1,2,0};
        int bottomViews[] = {0,2,0};
        int m[][] = {{2,3,1}, {1,2,3}, {3, 1, 2}};
//
//        int leftViews[] = {2,3,2,1};
//        int topViews[] = {2,1,3,2};
//        int rightViews[] = {2,1,2,3};
//        int bottomViews[]={1,3,2,3};
//        int m[][] = {{3,4,1,2}, {1,3,2,4}, {2,1,4,3},{4,2,3,1}};

//        int leftViews[] = {3, 2, 3, 2, 1};
//        int topViews[] = {4, 2, 1, 2, 3};
//        int rightViews[] = {3, 4, 1, 2, 2};
//        int bottomViews[] = {1, 4, 3, 2, 2};
//        int m[][] = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

        SkyscrapersProblem problem = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews,
                getSwapRules(m), m);
        EngineFactory factory = new EngineFactory();
        GPSEngine engine = factory.buildEngine(problem, SearchStrategy.DFS, null, 0);
        engine.findSolution();
    }

    public static List<Rule> getFillRules(int[][] m) {
        LinkedList rules = new LinkedList<SkyscrapersFillRule>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                for (int k = 1; k <= 3; k++){
                    SkyscrapersFillRule rule = new SkyscrapersFillRule(k, i, j);
                    rules.add(rule);
                }
            }
        }
        return rules;
    }

    public static List<Rule> getSwapRules(int[][] m) {
        LinkedList rules = new LinkedList<Rule>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < i; j++) {
                SkyscrapersSwapRowRule ruleRow = new SkyscrapersSwapRowRule(i, j);
                SkyscrapersSwapColRule ruleCol = new SkyscrapersSwapColRule(i, j);
                rules.add(ruleRow);
                rules.add(ruleCol);
            }
        }
        return rules;
    }
}
