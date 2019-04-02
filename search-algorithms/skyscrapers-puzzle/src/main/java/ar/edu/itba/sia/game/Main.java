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
        int[][] matrix;
        Scanner inputScanner = new Scanner(System.in);
        String gameMode;
        System.out.println("Welcome to the Skyscrapers Puzzle! " +
                "please enter F to play on Fill Mode or " +
                "S to play on Swap Mode");

        gameMode = Helpers.getGameMode(inputScanner);

        int dimensions = Helpers.getDimensions(inputScanner);
        if(Helpers.askInteractive(inputScanner)){
            InteractiveGame.playGame(gameMode, dimensions, inputScanner);
        }else{
            NonInteractiveGame.playGame(gameMode, dimensions, inputScanner);
        }

        inputScanner.close();
    }


}
