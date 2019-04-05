package ar.edu.itba.sia.game.UI;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.Permute;
import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.GPSNode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import static ar.edu.itba.sia.game.SkyscrapersPuzzle.solvePuzzle;

public class NonInteractiveGame {

    public static void playGame(String gameMode, int dimensions, Scanner inputScanner) {

        // Le pido nivel de dificultad --> Obtengo Los límites
        int difficultyLevel = Helpers.askDifficultyLevel(inputScanner);

        playGame(gameMode, dimensions, difficultyLevel);



    }

    public static void playGame(String gameMode, int dimensions, int difficultyLevel){
        Board generated_board = BoardGenerator.create(gameMode, dimensions, difficultyLevel);

        int[][] matrix;

        GPSEngine engine;
        long delta = 0;

        if( gameMode.equals("S"))
          matrix = Helpers.createSwapMatrix(dimensions);
        else
          matrix = new int[dimensions][dimensions];

        long start = System.nanoTime();

        engine = solvePuzzle(gameMode, dimensions, generated_board.getTopViews(),
                            generated_board.getBottomViews(), generated_board.getLeftViews(),
                            generated_board.getRightViews(), matrix);

        delta = System.nanoTime() - start;

        //Resuelvo

        if( engine.getSolutionNode() == null){
            System.out.println("The given board had no solution.");
        }else {
            System.out.println("Game ended, winning board: ");
            System.out.println(engine.getSolutionNode().getState().getRepresentation());
            System.out.println("Game mode " + gameMode);
            System.out.println("Dimensions " + dimensions);
            System.out.println("Depth of the solution: " + engine.getSolutionNode().getDepth());
            System.out.println("Total solution cost: " + engine.getSolutionNode().getCost());
            System.out.println("Qty of exploded nodes: " + engine.getExplosionCounter());
            System.out.println("Analized states # : " + engine.getBestCosts().size());
            System.out.println("# Frontier Nodes " + engine.getOpen().size());
            System.out.println("Time expended " + delta + " ns");
        //    engine.printSolutionPath();
        }


    }
}
