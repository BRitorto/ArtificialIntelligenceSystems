package ar.edu.itba.sia.game.heuristics;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.SkyscrapersState;

public class AdmissibleHeuristic implements Heuristic {

    @Override
    public Integer getValue(State state) {
        final SkyscrapersState ssState = (SkyscrapersState) state;
        final Board board = ssState.getCurrentBoard();

        return (int) Math.ceil((double)board.getBoardValidator().cantConflicts(board)/8.0);
    }

    public boolean isAdmissible() {
        return true;
    }
}
