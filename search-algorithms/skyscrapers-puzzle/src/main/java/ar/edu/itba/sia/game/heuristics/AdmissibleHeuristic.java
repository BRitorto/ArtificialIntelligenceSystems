package ar.edu.itba.sia.game.heuristics;

import ar.edu.itba.sia.api.Heuristic;
import ar.edu.itba.sia.api.State;
import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.SkyscrapersState;

public class AdmissibleHeuristic implements Heuristic {

    @Override
    public Integer getValue(State state) {
        final SkyscrapersState ssState = (SkyscrapersState) state;
        final Board board = ssState.getCurrentBoard();
        System.out.println(board.getBoardValidator().cantConflicts(board));

        return board.getBoardValidator().cantConflicts(board);
    }

    public boolean isAdmissible() {
        return true;
    }
}
