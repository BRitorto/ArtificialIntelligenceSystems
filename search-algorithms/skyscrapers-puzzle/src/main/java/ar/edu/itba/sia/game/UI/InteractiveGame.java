package ar.edu.itba.sia.game.UI;

import ar.edu.itba.sia.game.Permute;
import ar.edu.itba.sia.gps.GPSNode;

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
        GPSNode solutionNode = null;
        int matrix[][];
        if( gameMode.equals(FILL_MODE)) {
            matrix = new int[dimensions][dimensions];
            solutionNode = solvePuzzle(gameMode, dimensions, topView, bottomView, leftView, rightView, matrix);
        }else{
            Permute p = new Permute(dimensions);
            HashSet<Integer[]> permutations = p.getPermutations();
            Iterator<Integer[]> it = permutations.iterator();
            int counter=permutations.size();
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
                solutionNode = solvePuzzle(gameMode, dimensions, topView,
                        bottomView,leftView, rightView, matrix);

                if(solutionNode != null){
                    break;
                }
            }
        }

        if( solutionNode == null){
            System.out.println("The given board had no solution.");
        }else{
            System.out.println("Game ended, winning board: ");
            System.out.println(solutionNode.getState().getRepresentation());
        }


    }

}
