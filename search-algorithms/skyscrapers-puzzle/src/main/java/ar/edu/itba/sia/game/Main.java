package ar.edu.itba.sia.game;

import ar.edu.itba.sia.game.UI.Helpers;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import ar.edu.itba.sia.gps.EngineFactory;
import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;

import java.util.Scanner;

import static ar.edu.itba.sia.game.SkyscrapersPuzzle.solvePuzzle;

public class Main {

    private static final int TOP = 0;
    private static final int BOT = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    public static void main(String[] args){
        int[][] matrix;
        Scanner inputScanner = new Scanner(System.in);
        String gameMode;
        System.out.println("Welcome to the Skyscrapers Puzzle! " +
                "please enter F to play on Fill Mode or " +
                "S to play on Swap Mode");

        gameMode = Helpers.getGameMode(inputScanner);

        int dimensions = Helpers.getDimensions(inputScanner);
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

        GPSNode solutionNode = solvePuzzle(gameMode,dimensions, topView, bottomView, leftView, rightView);

        if( solutionNode == null){
            System.out.println("The given board had no solution.");
        }else{
            System.out.printf("Game ended, winning board: ");
            System.out.println(solutionNode.getState().getRepresentation());
        }

        inputScanner.close();
    }


}
