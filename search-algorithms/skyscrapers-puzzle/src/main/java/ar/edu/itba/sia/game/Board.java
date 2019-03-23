package ar.edu.itba.sia.game;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int[] topViews;
    private int[] bottomViews;
    private int[] leftViews;
    private int[] rightViews;
    private Skyscraper[][] matrix;

    public Board cloneBoard(){
        return new Board(this.bottomViews.length,this.topViews,this.bottomViews,this.leftViews,this.rightViews,this.matrix);
    }


    public Board(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews) {
        if (!sideViewsDimensionAreCorrect(dimension, topViews, bottomViews, leftViews, rightViews)) {
            throw new IllegalDimensionException();
        }
        this.matrix = new Skyscraper[dimension][dimension];
        this.topViews = topViews;
        this.bottomViews = bottomViews;
        this.leftViews = leftViews;
        this.rightViews = rightViews;
    }
    public Board(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews,Skyscraper[][] m) {
        if (!sideViewsDimensionAreCorrect(dimension, topViews, bottomViews, leftViews, rightViews)) {
            throw new IllegalDimensionException();
        }
        this.matrix = m;
        this.topViews = topViews;
        this.bottomViews = bottomViews;
        this.leftViews = leftViews;
        this.rightViews = rightViews;
    }


    public Board(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews,
                 int[][] initialMatrix) {
        if (!sideViewsDimensionAreCorrect(dimension, topViews, bottomViews, leftViews, rightViews)) {
            throw new IllegalDimensionException();
        }
        this.matrix = new Skyscraper[dimension][dimension];
        for (int i = 0; i < initialMatrix.length; i++) {
            for (int j = 0; j < initialMatrix[0].length; j++) {
                this.matrix[i][j] = new Skyscraper(i, j);
            }
        }
        this.topViews = topViews;
        this.bottomViews = bottomViews;
        this.leftViews = leftViews;
        this.rightViews = rightViews;
    }


    private Boolean sideViewsDimensionAreCorrect(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews){
        return topViews.length == dimension && bottomViews.length == dimension &&
                leftViews.length == dimension && rightViews.length == dimension;
    }
    public List<int[]> getViews() {
        LinkedList<int[]> list = new LinkedList<>();
        list.add(this.topViews);
        list.add(this.bottomViews);
        list.add(this.leftViews);
        list.add(this.rightViews);
        return list;
    }

    public void setMatrix(Skyscraper[][] matrix) {
        this.matrix = matrix;
    }

    public Skyscraper[][] getMatrix() {
        return matrix;
    }


    public void printMatrix(){
        int rows = this.matrix.length;
        int columns = this.matrix[0].length;
        String str = "|\t";

        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                str += this.matrix[i][j].getHeight() + "\t";
            }

            System.out.println(str + "|");
            str = "|\t";
        }
    }


}
