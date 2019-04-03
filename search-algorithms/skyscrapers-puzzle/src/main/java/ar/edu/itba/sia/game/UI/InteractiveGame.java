package ar.edu.itba.sia.game.UI;

import ar.edu.itba.sia.game.Permute;
import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.GPSNode;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import static ar.edu.itba.sia.game.SkyscrapersPuzzle.FILL_MODE;
import static ar.edu.itba.sia.game.SkyscrapersPuzzle.solvePuzzle;

public class InteractiveGame {
    private static final int TOP = 0;
    private static final int BOT = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    public static void playGame(String gameMode, int dimensions, Scanner inputScanner){
        int[] topView = new int[dimensions], bottomView = new int[dimensions];
        int[] rightView = new int[dimensions], leftView = new int[dimensions];

        System.out.println("Now you need to write the top, bottom, left and right restrictions" +
                " separate each number by a space");
        for(int i=0; i < 4; i++){
            switch(i){
                case TOP:
                    System.out.println("Write the top side restrictions:");
                    topView = Helpers.getRestrictionArray(inputScanner, dimensions);
                    break;
                case BOT:
                    System.out.println("Write the bottom side restrictions:");
                    bottomView = Helpers.getRestrictionArray(inputScanner, dimensions);
                    break;
                case LEFT:
                    System.out.println("Write the left side restrictions");
                    leftView = Helpers.getRestrictionArray(inputScanner, dimensions);
                    break;
                case RIGHT:
                    System.out.println("Write the right side restrictions");
                    rightView = Helpers.getRestrictionArray(inputScanner, dimensions);
                    break;
            }
        }
        GPSEngine engine = null;
        long delta = 0;
        int matrix[][];
        if( gameMode.equals(FILL_MODE)) {
            matrix = new int[dimensions][dimensions];
            engine = solvePuzzle(gameMode, dimensions, topView, bottomView, leftView, rightView, matrix);
        }else{
            Permute p = new Permute(dimensions);
            HashSet<Integer[]> permutations = p.getPermutations();
            Iterator<Integer[]> it = permutations.iterator();
            int counter=permutations.size();
            long start = System.nanoTime();
            while(it.hasNext()) {
                Integer[] aux = it.next();
                matrix = new int[dimensions][dimensions];
                int index = 0;
                for (int i = 0; i < dimensions; i++) {
                    for (int j = 0; j < dimensions; j++) {
                        matrix[i][j] = aux[(j + index) % dimensions];
                    }
                    index++;
                }
                engine = solvePuzzle(gameMode, dimensions, topView,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      bottomView,leftView, rightView, matrix);

                if(engine.getSolutionNode() != null){
                    break;
                }
            }
            delta = System.nanoTime() - start;
        }

        if( engine.getSolutionNode() == null){
            System.out.println("The given board had no solution.");
        }else{
            System.out.println("Game ended, winning board: ");
            System.out.println(engine.getSolutionNode().getState().getRepresentation());
            System.out.println("Depth of the solution: " + engine.getSolutionNode().getDepth());
            System.out.println("Total solution cost: " + engine.getSolutionNode().getCost());
            System.out.println("Qty of exploded nodes: " + engine.getExplosionCounter());
            System.out.println("Analized states # : " + engine.getBestCosts().size());
            System.out.println("# Frontier Nodes " + engine.getOpen().size());
            System.out.println("Time expended " + delta + " ns");


        try {
            PrintStream fileOut = new PrintStream("./solutionPath.txt");
            System.setOut(fileOut);
            engine.printSolutionPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        }


    }

}
