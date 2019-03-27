package ar.edu.itba.sia.factories;


import ar.edu.itba.sia.game.Board;

public class BoardFactory {
    private static final int[] dummy_arr = {1,2,3,4,5};
    private int[] topViews, bottomViews, leftViews, rightViews;
    private int dimension;

    public static Board createCorrectBoard(){
        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,2,3};
        int bottomViews[]={1,2,3};
        int m[][] = {{2, 1, 3}, {1, 3, 2}, {3, 2, 1}};

        return new Board(3, topViews, bottomViews, leftViews, rightViews, m);
    }

    public static Board createIncompleteBoard(){
        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,2,3};
        int bottomViews[]={1,2,3};
        int m[][] = {{2, 1, 3}, {1, 0, 0}, {0, 0, 0}};

        return new Board(3, topViews, bottomViews, leftViews, rightViews, m);

    }

    public static Board createWithCustomMatrix(int[][] m){
        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,2,3};
        int bottomViews[]={1,2,3};

        return new Board(3, topViews, bottomViews, leftViews, rightViews, m);
    }

}

