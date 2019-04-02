package ar.edu.itba.sia.game.UI;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.gps.GPSNode;

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

        Board generated_board = BoardGenerator.create(gameMode, dimensions, difficultyLevel);
        // Genero un board en base al nivel de dificultad si es SWAP
        //Badly Hardocded --> TODO FIX THIS
        int[][] matrix;
        if (gameMode.equals("S")) {
            if (dimensions == 3) {
                matrix = validMatrix3;
            } else {
                matrix = validMatrix4;
            }
        }else{
            matrix = new int[dimensions][dimensions];
        }

        GPSNode solutionNode = solvePuzzle(gameMode,dimensions, generated_board.getTopViews(),
                                            generated_board.getBottomViews(), generated_board.getLeftViews(),
                                            generated_board.getRightViews(), matrix);
        //Resuelvo

        if( solutionNode == null){
            System.out.println("The given board had no solution.");
        }else{
            System.out.println("Game ended, winning board: ");
            System.out.println(solutionNode.getState().getRepresentation());
        }


    }
}
