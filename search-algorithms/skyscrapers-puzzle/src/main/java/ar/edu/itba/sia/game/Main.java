package ar.edu.itba.sia.game;

import ar.edu.itba.sia.game.UI.Helpers;
import ar.edu.itba.sia.game.UI.InteractiveGame;
import ar.edu.itba.sia.game.UI.NonInteractiveGame;
import ar.edu.itba.sia.gps.SearchStrategy;

import java.util.Scanner;

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
            if (Helpers.askInteractive(inputScanner)) {
                InteractiveGame.playGame(gameMode, dimensions, inputScanner);
            } else {
                NonInteractiveGame.playGame(gameMode, dimensions, inputScanner);
            }

            inputScanner.close();
            return;
        }

        //Si el if anterior no se cumplió sabemos que args.lenght == 1
        // Pues la verificación de tirar error si es != 1 la hace el script mismo

        switch (args[0]){
            case "1":
                System.out.println("SOLVING 3X3 BOARD IN EASY MODE WITH FILL RULE DFS");
                NonInteractiveGame.playGame("F", SearchStrategy.DFS, 3, 1);
                break;
            case "2":
                System.out.println("SOLVING 4X4 BOARD IN HARD MODE WITH SWAP RULE, GREEDY");
                NonInteractiveGame.playGame("S",SearchStrategy.GREEDY, 4, 3);
                break;
            case "3":
                System.out.println("SOLVING 5X5 BOARD IN MEDIUM MODE WITH SWAP RULE ASTAR");
                NonInteractiveGame.playGame("S",SearchStrategy.ASTAR, 5, 2);
                break;
            default:
                System.out.println("SOLVING 4X4 BOARD IN HARD MODE WITH FILL RULE, IDDFS");
                NonInteractiveGame.playGame("F", SearchStrategy.IDDFS, 4, 3);
                break;
        }
    }


}
