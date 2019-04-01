package ar.edu.itba.sia.game;

import ar.edu.itba.sia.gps.api.State;

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
        int[] rightView = currentBoard.getRightViews();
        int[] leftView = currentBoard.getLeftViews();
        int[] topView = currentBoard.getTopViews();
        int[] bottomView = currentBoard.getBottomViews();

        int rows = m.length;
        int columns = m[0].length;

        StringBuilder str = new StringBuilder("\t\t");

        for (int i = 0; i < topView.length; i++) {
            str.append(topView[i]);
            str.append("\t");
        }
        str.append("\n\t");
        for (int i = 0; i < (topView.length)*2+3; i++) {
            str.append("─");
            str.append(" ");
        }
        str.append("\n\t");
        for(int i=0; i<rows; i++) {
            str.append(leftView[i]);
            str.append("|\t");
            for(int j=0; j<columns; j++){
                str.append(m[i][j].getHeight());
                str.append("\t");
            }
            str.append("|");
            str.append(rightView[i]);
            str.append("\n\t");
        }
        for (int i = 0; i < (bottomView.length)*2+3; i++) {
            str.append("─");
            str.append(" ");
        }
        str.append("\n\t\t");
        for (int i = 0; i < bottomView.length; i++) {
            str.append(bottomView[i]);
            str.append("\t");
        }
        return str.toString();
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

    @Override
    public boolean equals(Object state) {
        if (!(state instanceof SkyscrapersState)) {
            return false;
        }
        SkyscrapersState myState = (SkyscrapersState) state;
        Skyscraper[][] matrix = this.getCurrentBoard().getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (!(matrix[i][j].getHeight() == myState.getCurrentBoard().getMatrix()[i][j].getHeight())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return getCurrentBoard().hashCode();
    }
}
