package ar.edu.itba.sia.game.UI;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.SkyscrapersPuzzle;

public class BoardGenerator {

    private static int[][] validMatrix3;
    private static int[][] validMatrix4;
    private static int[][] validMatrix5;

    public static Board create(String gameMode, int dimensions, int difficultyLevel) {

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
                return createEasyBoardDim5(gameMode);
        }

        return null;
    }

    private static Board createEasyBoardDim3(String gameMode) {
        int[] topView = {3, 0, 1};
        int[] bottomView = {1, 2, 0};
        int[] leftView = {3, 0, 0};
        int[] rightView = {1, 0, 2};


        return new Board(3, topView, bottomView, leftView, rightView);


    }

    private static Board createEasyBoardDim4(String gameMode) {
        int[] topView = {1, 0, 3, 0};
        int[] bottomView = {0, 2, 0, 1};
        int[] leftView = {1, 2, 0, 4};
        int[] rightView = {4, 0, 2, 1};

        return new Board(4, topView, bottomView, leftView, rightView);

    }

    private static Board createEasyBoardDim5(String gameMode) {
        int[] topView = {5, 0, 0, 2, 1};
        int[] bottomView = {1, 0, 0, 2, 2};
        int[] leftView = {0, 0, 3, 2, 0};
        int[] rightView = {1, 0, 2, 0, 0};


        return new Board(5, topView, bottomView, leftView, rightView);

    }

    private static Board createMediumBoard(String gameMode, int dimensions) {
        switch(dimensions){
            case 3:
                return createMediumBoardDim3(gameMode);
            case 4:
                return createMediumBoarDim4(gameMode);
            case 5:
                return createMediumBoardDim5(gameMode);
        }

        return null;
    }

    private static Board createMediumBoardDim3(String gameMode) {
        int[] topView = {2, 1, 3};
        int[] bottomView = {2, 1, 0};
        int[] leftView = {2, 0, 2};
        int[] rightView = {1, 3, 0};

        return new Board(3, topView, bottomView, leftView, rightView);

    }

    private static Board createMediumBoarDim4(String gameMode) {
        int[] topView = {2, 0, 3, 1};
        int[] bottomView = {2, 1, 2, 3};
        int[] leftView = {0, 1, 3, 2};
        int[] rightView = {1, 0, 2, 3};

        return new Board(4, topView, bottomView, leftView, rightView);

    }

    private static Board createMediumBoardDim5(String gameMode) {
        int[] topView = {5, 4, 3, 2, 1};
        int[] bottomView = {1, 0, 0, 2, 2};
        int[] leftView = {5, 0, 3, 2, 0};
        int[] rightView = {1, 0, 2, 2, 0};

        return new Board(5, topView, bottomView, leftView, rightView);

    }

    private static Board createHardBoard(String gameMode, int dimensions) {
        switch(dimensions){
            case 3:
                return createHardBoardDim3(gameMode);
            case 4:
                return createHardBoarDim4(gameMode);
            case 5:
                return createHardBoardDim5(gameMode);
        }

        return null;
    }


    private static Board createHardBoardDim3(String gameMode) {
        int[] topView = {1, 2, 3};
        int[] bottomView = {2, 2, 1};
        int[] leftView = {1, 2, 2};
        int[] rightView = {3, 2, 1};


        return new Board(3, topView, bottomView, leftView, rightView);

    }

    private static Board createHardBoarDim4(String gameMode) {
        int[] topView = {3, 1, 2, 0};
        int[] bottomView = {1, 0, 3, 2};
        int[] leftView = {0, 2, 4, 0};
        int[] rightView = {2, 0, 1, 4};


        return new Board(4, topView, bottomView, leftView, rightView);

    }

    private static Board createHardBoardDim5(String gameMode) {
        int[] topView = {5, 4, 3, 2, 1};
        int[] bottomView = {1, 2, 2, 2, 2};
        int[] leftView = {5, 4, 3, 2, 1};
        int[] rightView = {1, 2, 2, 2, 2};

        return new Board(5, topView, bottomView, leftView, rightView);
    }



}
