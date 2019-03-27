package ar.edu.itba.sia.game;

import ar.edu.itba.sia.api.State;

import java.awt.*;

public class SkyscrapersState implements State {
    private Board currentBoard;

    public SkyscrapersState(Board board) {
        this.currentBoard = board;
    }

    @Override
    public String getRepresentation() {
        return printMatrix(currentBoard.getMatrix());
    }

    public String printMatrix(Skyscraper[][] m){
        int rows = m.length;
        int columns = m[0].length;
        String str = "|\t";

        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++){
                str += m[i][j].getHeight() + "\t";
            }
            str += "|" + "\n" + "|\t";
        }
        return str;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public Point lastPositionToFill(){
        Skyscraper[][] auxM=getCurrentBoard().getMatrix();
        for(int i=0;i<auxM.length;i++){
            for(int j=0;j<auxM.length;j++){
                if(auxM[i][j].getHeight()==0){
                    return new Point(i,j);
                }
            }
        }
        return null;
    }
}
