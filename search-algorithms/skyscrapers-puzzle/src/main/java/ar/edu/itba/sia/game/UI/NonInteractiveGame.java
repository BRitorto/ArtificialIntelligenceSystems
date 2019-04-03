package ar.edu.itba.sia.game.UI;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.Permute;
import ar.edu.itba.sia.gps.GPSNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import static ar.edu.itba.sia.game.SkyscrapersPuzzle.solvePuzzle;

public class NonInteractiveGame {

    static int[][] validMatrix3 = new int[][]
        {{1, 2, 3},
         {2, 3, 1},
         {3, 1, 2}};

    static int[][] validMatrix4 = new int[][]
        {{1, 2, 3, 4},
        {2, 3, 4, 1},
        {3, 4, 1, 2},
        {4, 1, 2, 3}};

    public static void playGame(String gameMode, int dimensions, Scanner inputScanner) {

        // Le pido nivel de dificultad --> Obtengo Los límites
        int difficultyLevel = Helpers.askDifficultyLevel(inputScanner);

        playGame(gameMode, dimensions, difficultyLevel);



    }

    public static void playGame(String gameMode, int dimensions, int difficultyLevel){
        Board generated_board = BoardGenerator.create(gameMode, dimensions, difficultyLevel);


        int[][] matrix;
      /*  if (gameMode.equals("S")) {
            if (dimensions == 3) {
                matrix = validMatrix3;
            } else {
                matrix = validMatrix4;
            }
        }else{
            matrix = new int[dimensions][dimensions];
        }*/
      //Initialized to get rid of "might not be initialized" error
      GPSNode solutionNode = null;
        if (gameMode.equals("S")) {
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
                solutionNode = solvePuzzle(gameMode, dimensions, generated_board.getTopViews(),
                        generated_board.getBottomViews(), generated_board.getLeftViews(),
                        generated_board.getRightViews(), matrix);

                if(solutionNode != null){
                    break;
                }
            }

        }else {

            matrix = new int[dimensions][dimensions];
            solutionNode = solvePuzzle(gameMode, dimensions, generated_board.getTopViews(),
                    generated_board.getBottomViews(), generated_board.getLeftViews(),
                    generated_board.getRightViews(), matrix);
        }

        //Resuelvo

        if( solutionNode == null){
            System.out.println("The given board had no solution.");
        }else{
            System.out.println("Game ended, winning board: ");
            System.out.println(solutionNode.getState().getRepresentation());
        }

    }

    public void garbage2(){
        int n = 5;
//        Permute p = new Permute(n);
    //
    //        HashSet<Integer[]> permutations = (HashSet<Integer[]>) p.getPermutations();
    //        Iterator<Integer[]> it = permutations.iterator();
    //        int lel = permutations.size();
    //        while(it.hasNext()){
    //            Integer[] aux = it.next();
    //            int m[][] = new int[n][n];
    //            int index = 0;
    //            for(int i=0 ; i< n ; i++){
    //                for(int j=0 ; j< n ; j++){
    //                    m[i][j] = aux[(j+index)%n];
    //                }
    //                index++;
    //            }
    //
    //            System.out.println("-------------------------");
    //            for (int i = 0; i < m.length; i++) {
    //                for (int j = 0; j < m[i].length; j++) {
    //                    System.out.print(m[i][j] + " ");
    //                }
    //                System.out.println();
    //            }
    //            System.out.println("quedan esta cantidad de matrices = "+lel--);
    //            SkyscrapersProblem problemSwap = new SkyscrapersProblem(n, topViewsSwap5, bottomViewsSwap5, leftViewsSwap5, rightViewsSwap5,
    //                    SkyscrapersPuzzle.getSwapRules(m), m);
    }
}