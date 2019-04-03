package ar.edu.itba.sia.game;

import ar.edu.itba.sia.game.UI.Helpers;
import ar.edu.itba.sia.game.UI.InteractiveGame;
import ar.edu.itba.sia.game.UI.NonInteractiveGame;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import ar.edu.itba.sia.gps.EngineFactory;
import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;

import java.util.Scanner;

import static ar.edu.itba.sia.game.SkyscrapersPuzzle.solvePuzzle;

public class Main {

    public static void main(String[] args){


        if (args.length == 0) {
            int[][] matrix;
            Scanner inputScanner = new Scanner(System.in);
            String gameMode;
            System.out.println("Welcome to the Skyscrapers Puzzle! " +
                    "please enter F to play on Fill Mode or " +
                    "S to play on Swap Mode");

            gameMode = Helpers.getGameMode(inputScanner);

            int dimensions = Helpers.getDimensions(inputScanner);
            NonInteractiveGame.playGame(gameMode, dimensions, inputScanner);

            inputScanner.close();
            return;
        }

        //Si el if anterior no se cumplió sabemos que args.lenght == 1
        // Pues la verificación de tirar error si es != 1 la hace el script mismo

        switch (args[0]){
            case "1":
                System.out.println("SOLVING 3X3 BOARD IN EASY MODE WITH FILL RULE");
                NonInteractiveGame.playGame("F", 3, 1);
                break;
            case "2":
                System.out.println("SOLVING 4X4 BOARD IN HARD MODE WITH SWAP RULE");
                NonInteractiveGame.playGame("S", 4, 3);
                break;
            case "3":
                System.out.println("SOLVING 5X5 BOARD IN MEDIUM MODE WITH SWAP RULE");
                NonInteractiveGame.playGame("S", 5, 2);
                break;
            default:
                System.out.println("SOLVING 4X4 BOARD IN HARD MODE WITH FILL RULE");
                NonInteractiveGame.playGame("F", 4, 3);
                break;
        }
    }


}
