package ar.edu.itba.sia.game;

import ar.edu.itba.sia.game.rules.SkyscrapersSwapRule;
import ar.edu.itba.sia.gps.EngineFactory;
import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import ar.edu.itba.sia.game.rules.SkyscrapersFillRule;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SkyscrapersPuzzle {
    public static final String FILL_MODE = "F";
    public static final String SWAP_MODE = "S";


    public static GPSEngine solvePuzzle(String gameMode, SearchStrategy strategy, int dimensions, int[] topView, int[] bottomView, int[] leftView, int[] rightView, int[][] matrix) {
        SkyscrapersProblem problem;
        EngineFactory factory;
        GPSEngine engine;

        if(gameMode.equals(FILL_MODE)){
            problem = new SkyscrapersProblem(dimensions, topView, bottomView, leftView, rightView,
                    getFillRules(matrix), matrix);
            factory = new EngineFactory();
            engine = factory.buildEngine(problem, strategy, null, 0);
            engine.findSolution();
        }else{
            problem = new SkyscrapersProblem(dimensions, topView, bottomView, leftView, rightView,
                    getSwapRules(matrix), matrix);
            factory = new EngineFactory();
            engine = factory.buildEngine(problem, strategy, new AdmissibleHeuristic(), 0);
            engine.findSolution();
        }

        return engine;
    }

    public static List<Rule> getFillRules(int[][] m) {
        LinkedList rules = new LinkedList<SkyscrapersFillRule>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                for (int k = 1; k <= m.length; k++){
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
            for (int j = 0; j < m.length; j++) {
                Rule rule = new SkyscrapersSwapRule(new Point(i, j), new Point(i, (j+1)%m.length));
                rules.add(rule);
            }
        }
        return rules;
    }
}