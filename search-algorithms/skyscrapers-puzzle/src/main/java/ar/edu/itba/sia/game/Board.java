package ar.edu.itba.sia.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Board{
    private int[] topViews;
    private int[] bottomViews;
    private int[] leftViews;
    private int[] rightViews;
    private Skyscraper[][] matrix;
    private BoardValidator boardValidator;

    public Board cloneBoard(){
        Skyscraper [][] aux=new Skyscraper[matrix.length][matrix.length];
        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix.length;j++){
                if(this.matrix[i][j].getHeight()!=0)
                    aux[i][j]=this.matrix[i][j];
                else
                    aux[i][j]=new Skyscraper(i,j,0);
            }
        }
        return new Board(this.bottomViews.length,this.topViews,this.bottomViews,this.leftViews,this.rightViews,aux);
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
        this.boardValidator = new BoardValidator();
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
        this.boardValidator = new BoardValidator();

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
        this.boardValidator = new BoardValidator();

    }

    private Boolean sideViewsDimensionAreCorrect(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews){
        return topViews.length == dimension && bottomViews.length == dimension &&
                leftViews.length == dimension && rightViews.length == dimension;
    }

    public void setMatrix(Skyscraper[][] matrix) {
        this.matrix = matrix;
    }

    public Skyscraper[][] getMatrix() {
        return matrix;
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

    public Optional<Board> SwapRows(int row1, int row2) {
        Skyscraper[][] auxMatrix = getMatrix();
        Skyscraper[] auxRow = auxMatrix[row1];
        auxMatrix[row1] = auxMatrix[row2];
        auxMatrix[row2] = auxRow;
        Board board = new Board(auxMatrix.length, topViews, bottomViews, leftViews, rightViews, auxMatrix);
        if (getBoardValidator().cantConflicts(board) != 0){
            return Optional.empty();
        }
        return Optional.of(board);
    }

    public Optional<Board> SwapCols(int col1, int col2) {
        Skyscraper[][] auxMatrix = getMatrix();
        for (int i = 0; i < auxMatrix.length; i++) {
            Skyscraper aux = auxMatrix[i][col1];
            auxMatrix[i][col1] = auxMatrix[i][col2];
            auxMatrix[i][col2] = aux;
        }
        Board board = new Board(auxMatrix.length, topViews, bottomViews, leftViews, rightViews, auxMatrix);
        if (getBoardValidator().cantConflicts(board) != 0){
            return Optional.empty();
        }
        return Optional.of(board);
    }

    public boolean isComplete() {
        return this.checkEmptySpaces() == 0;
    }


    //GETTERS FOR THE PEOPLE

    public List<int[]> getViews() {
        LinkedList<int[]> list = new LinkedList<>();
        list.add(this.topViews);
        list.add(this.bottomViews);
        list.add(this.leftViews);
        list.add(this.rightViews);
        return list;
    }

    public int[] getTopViews(){
        return topViews;
    }

    public int[] getBottomViews(){
        return bottomViews;
    }

    public int[] getLeftViews(){
        return leftViews;
    }

    public int[] getRightViews(){
        return rightViews;
    }

    public BoardValidator getBoardValidator() {
        return this.boardValidator;
    }
}
