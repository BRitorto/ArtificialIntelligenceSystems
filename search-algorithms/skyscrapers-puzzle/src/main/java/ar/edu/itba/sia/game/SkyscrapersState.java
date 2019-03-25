package ar.edu.itba.sia.game;

import ar.edu.itba.sia.api.State;

public class SkyscrapersState implements State {
    private Board currentBoard;

    public SkyscrapersState(Board board) {
        this.currentBoard = board;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    @Override
    public String getRepresentation() {
        return null;
    }

    public void printMatrix(Skyscraper[][] m){
        int rows = m.length;
        int columns = m[0].length;
        String str = "|\t";

        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                str += m[i][j].getHeight() + "\t";
            }

            System.out.println(str + "|");
            str = "|\t";
        }
    }
}
