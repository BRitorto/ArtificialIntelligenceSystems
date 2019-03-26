package ar.edu.itba.sia.game;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Board {
    private int[] topViews;
    private int[] bottomViews;
    private int[] leftViews;
    private int[] rightViews;
    private Skyscraper[][] matrix;
    private List<Point> fixedCells;
    private int emptySpaces;

    public Board cloneBoard(){
        return new Board(this.bottomViews.length,this.topViews,this.bottomViews,this.leftViews,this.rightViews,this.matrix);
    }


    public Board(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews) {
        if (!sideViewsDimensionAreCorrect(dimension, topViews, bottomViews, leftViews, rightViews)) {
            throw new IllegalDimensionException();
        }
        this.matrix = new Skyscraper[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.matrix[i][j] = new Skyscraper(i, j);
            }
        }
        this.topViews = topViews;
        this.bottomViews = bottomViews;
        this.leftViews = leftViews;
        this.rightViews = rightViews;
    }

    private Board(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews,Skyscraper[][] m) {
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
                this.matrix[i][j] = new Skyscraper(i, j, initialMatrix[i][j]);
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

    public boolean isFull(){
        return emptySpaces == 0;
    }

    public int checkEmptySpaces(){
        int rows = this.matrix.length;
        int columns = this.matrix[0].length;
        int count = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
               if(this.matrix[i][j].getHeight() == 0)
                   count++;
            }
        }
        return count;
    }

    public boolean isValidSwap(Point pos1, Point pos2) {
        for(Point p: this.fixedCells){
            if(p.equals(pos1) || p.equals(pos2))
                return false;
        }
        return true;
    }

    public Board swapValue(Point pos1, Point pos2) {
        Board board = this.cloneBoard();

        Skyscraper aux = this.matrix[(int) pos1.getX()][(int) pos1.getY()];
        board.matrix[(int) pos1.getX()][(int) pos1.getY()] = this.matrix[(int) pos2.getX()][(int) pos2.getY()];
        board.matrix[(int) pos2.getX()][(int) pos2.getY()] = aux;

        return board;
    }


    public boolean isComplete() {
        return this.checkEmptySpaces() == 0;
    }
}
