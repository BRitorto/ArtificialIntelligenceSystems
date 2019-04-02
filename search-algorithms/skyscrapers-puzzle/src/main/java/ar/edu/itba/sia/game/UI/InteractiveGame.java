package ar.edu.itba.sia.game.UI;

import ar.edu.itba.sia.gps.GPSNode;

import java.util.Scanner;

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

        int matrix[][] = initializeMatrix(dimensions);
        GPSNode solutionNode = solvePuzzle(gameMode,dimensions, topView, bottomView, leftView, rightView, matrix);

        if( solutionNode == null){
            System.out.println("The given board had no solution.");
        }else{
            System.out.println("Game ended, winning board: ");
            System.out.println(solutionNode.getState().getRepresentation());
        }


    }

    //Hacer este método más piola más adelante
    private static int[][] initializeMatrix(int dimensions) {
        //int m[][] = {{2,1,4,3},{3,4,1,2},{4,2,3,1},{1,3,2,4}};
        int m[][];
        if(dimensions == 4) {
            m = new int[][]{{3, 4, 1, 2}, {1, 3, 2, 4}, {2, 1, 4, 3}, {4, 2, 3, 1}};
        }else{
            m = new int[][]{{1, 2, 3}, {2, 3, 1}, {3, 1, 2}};
        }

        return m;
    }
}
