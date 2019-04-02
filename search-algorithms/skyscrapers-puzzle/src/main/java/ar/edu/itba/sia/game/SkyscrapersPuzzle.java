package ar.edu.itba.sia.game;

import ar.edu.itba.sia.gps.EngineFactory;
import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import ar.edu.itba.sia.game.rules.SkyscrapersFillRule;
import ar.edu.itba.sia.game.rules.SkyscrapersSwapColRule;
import ar.edu.itba.sia.game.rules.SkyscrapersSwapRowRule;

import javax.swing.text.html.Option;
import java.text.ParseException;
import java.util.*;

public class SkyscrapersPuzzle {
    public static final String FILL_MODE = "F";
    public static final String SWAP_MODE = "S";


    public static GPSNode solvePuzzle(String gameMode, int dimensions, int[] topView, int[] bottomView, int[] leftView, int[] rightView, int[][] matrix) {
        SkyscrapersProblem problem;
        EngineFactory factory;
        GPSEngine engine;

        if(gameMode.equals(FILL_MODE)){
            problem = new SkyscrapersProblem(dimensions, topView, bottomView, leftView, rightView,
                    getFillRules(matrix), matrix);
            factory = new EngineFactory();
            engine = factory.buildEngine(problem, SearchStrategy.BFS, null, 0);
            engine.findSolution();
        }else{
            problem = new SkyscrapersProblem(dimensions, topView, bottomView, leftView, rightView,
                    getSwapRules(matrix), matrix);
            factory = new EngineFactory();
            engine = factory.buildEngine(problem, SearchStrategy.GREEDY, new AdmissibleHeuristic(), 0);
            engine.findSolution();
        }

        return engine.getSolutionNode();
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
            for (int j = i+1; j < m.length; j++) {
                SkyscrapersSwapRowRule ruleRow = new SkyscrapersSwapRowRule(i, j);
                SkyscrapersSwapColRule ruleCol = new SkyscrapersSwapColRule(i, j);
                rules.add(ruleRow);
                rules.add(ruleCol);
            }
        }
        return rules;
    }

    private void garbage(){
        int topViews[] = {2,1,3,2};
        int bottomViews[]={1,3,2,3};
        int leftViews[] = {2,3,2,1};
        int rightViews[] = {2,1,2,3};
        int m[][] = {{2,1,4,3},{3,4,1,2},{4,2,3,1},{1,3,2,4}};

       // int leftViews[] = {3, 2, 3, 2, 1};
//        int topViews[] = {4, 2, 1, 2, 3};
//        int rightViews[] = {3, 4, 1, 2, 2};
//        int bottomViews[] = {1, 4, 3, 2, 2}

        //int topViews[] = {4,3,2,1};
//        int leftViews[] = {0,0,0,0};
//        int rightViews[] = {1,2,2,2};
//        int bottomViews[]={0,0,0,0};
//        int m[][] = {{3,4,1,2}, {1,3,2,4}, {2,1,4,3},{4,2,3,1}};
    }
}