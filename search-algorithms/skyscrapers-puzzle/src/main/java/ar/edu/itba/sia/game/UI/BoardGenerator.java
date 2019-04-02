package ar.edu.itba.sia.game.UI;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.SkyscrapersPuzzle;

public class BoardGenerator {

    private static int[][] validMatrix3;
    private static int[][] validMatrix4;
    private static int[][] validMatrix5;

    public static Board create(String gameMode, int dimensions, int difficultyLevel) {
            validMatrix3 = new int[][]
                    {{1, 2, 3},
                     {2, 3, 1},
                     {3, 1, 2}};

            validMatrix4 = new int[][]
                    {{1, 2, 3, 4},
                     {2, 3, 4, 1},
                     {3, 4, 1, 2},
                     {4, 1, 2, 3}};

        switch (difficultyLevel){
            case 1:
                return createEasyBoard(gameMode, dimensions);
            case 2:
                return createMediumBoard(gameMode, dimensions);
            case 3:
                return createHardBoard(gameMode, dimensions);
            default:
                return createEasyBoard(gameMode, dimensions);
        }
    }

    private static Board createEasyBoard(String gameMode, int dimensions) {
        switch(dimensions){
            case 3:
                return createEasyBoardDim3(gameMode);
            case 4:
                return createEasyBoardDim4(gameMode);
            case 5:
               //TODO
        }

        return null;
    }

    private static Board createMediumBoard(String gameMode, int dimensions) {
        switch(dimensions){
            case 3:
                return createMediumBoardDim3(gameMode);
            case 4:
                return createMediumBoarDim4(gameMode);
            case 5:
                //TODO
        }

        return null;
    }

    private static Board createHardBoard(String gameMode, int dimensions) {
        switch(dimensions){
            case 3:
                return createHardBoardDim3(gameMode);
            case 4:
                return createHardBoarDim4(gameMode);
            case 5:
                //TODO
        }

        return null;
    }

    private static Board createEasyBoardDim4(String gameMode) {
        int[] topView = {1, 0, 3, 0};
        int[] bottomView = {0, 2, 0, 1};
        int[] leftView = {1, 2, 0, 4};
        int[] rightView = {4, 0, 2, 1};

        if(isFillMode(gameMode)){
            return new Board(4, topView, bottomView, leftView, rightView);
        }else{
            return new Board(4, topView, bottomView, leftView, rightView, validMatrix4);
        }
    }


    private static Board createEasyBoardDim3(String gameMode) {
        int[] topView = {3, 0, 1};
        int[] bottomView = {1, 2, 0};
        int[] leftView = {3, 0, 0};
        int[] rightView = {1, 0, 2};
        if(isFillMode(gameMode)){
           return new Board(3, topView, bottomView, leftView, rightView);
        }else{

            return new Board(3, topView, bottomView, leftView, rightView, validMatrix3);
        }

    }

    private static Board createMediumBoardDim3(String gameMode) {
        int[] topView = {2, 1, 3};
        int[] bottomView = {2, 1, 0};
        int[] leftView = {2, 0, 2};
        int[] rightView = {1, 3, 0};

        if(isFillMode(gameMode)){
            return new Board(3, topView, bottomView, leftView, rightView);
        }else{
            return new Board(3, topView, bottomView, leftView, rightView, validMatrix3);
        }
    }

    private static Board createMediumBoarDim4(String gameMode) {
        int[] topView = {2, 0, 3, 1};
        int[] bottomView = {2, 1, 2, 3};
        int[] leftView = {0, 1, 3, 2};
        int[] rightView = {1, 0, 2, 3};

        if(isFillMode(gameMode)){
            return new Board(4, topView, bottomView, leftView, rightView);
        }else{
            return new Board(4, topView, bottomView, leftView, rightView, validMatrix4);
        }
    }

    private static Board createHardBoardDim3(String gameMode) {
        int[] topView = {1, 2, 3};
        int[] bottomView = {2, 2, 1};
        int[] leftView = {1, 2, 2};
        int[] rightView = {3, 2, 1};

        if(isFillMode(gameMode)){
            return new Board(3, topView, bottomView, leftView, rightView);
        }else{
            return new Board(3, topView, bottomView, leftView, rightView, validMatrix3);
        }
    }

    private static Board createHardBoarDim4(String gameMode) {
        int[] topView = {1, 2, 3, 4};
        int[] bottomView = {2, 2, 2, 1};
        int[] leftView = {1, 2, 3, 4};
        int[] rightView = {4, 3, 2, 1};

        if(isFillMode(gameMode)){
            return new Board(4, topView, bottomView, leftView, rightView);
        }else{
            return new Board(4, topView, bottomView, leftView, rightView, validMatrix4);
        }
    }

    private static boolean isFillMode(String gameMode){
        return gameMode.equals(SkyscrapersPuzzle.FILL_MODE);
    }


}
