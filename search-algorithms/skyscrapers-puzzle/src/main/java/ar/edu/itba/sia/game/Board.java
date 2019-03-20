package ar.edu.itba.sia.game;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int[] topViews;
    private int[] bottomViews;
    private int[] leftViews;
    private int[] rightViews;
    private Skyscraper[][] matrix;

    public Board(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews) {
        if (topViews.length != dimension || bottomViews.length != dimension ||
                leftViews.length != dimension || rightViews.length != dimension) {
            throw new RuntimeException("Wrong dimensions of side numbers");
        }
        this.matrix = new Skyscraper[dimension][dimension];
        this.topViews = topViews;
        this.bottomViews = bottomViews;
        this.leftViews = leftViews;
        this.rightViews = rightViews;
    }

    public Board(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews,
                 int[][] initialMatrix) {
        if (topViews.length != dimension || bottomViews.length != dimension ||
                leftViews.length != dimension || rightViews.length != dimension) {
            throw new RuntimeException("Wrong dimensions of side numbers");
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
}
