package ar.edu.itba.sia.game.heuristics;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.SkyscrapersState;

public class NonAdmissibleHeuristic implements Heuristic {

    @Override
    public Integer getValue(State state) {
        final SkyscrapersState ssState = (SkyscrapersState) state;
        final Board board = ssState.getCurrentBoard();
        return board.getBoardValidator().cantConflicts(board);
    }

    public boolean isAdmissible() { return false; }
}
