package ar.edu.itba.sia.game.heuristics;

import ar.edu.itba.sia.api.Heuristic;
import ar.edu.itba.sia.api.State;
import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.BoardValidator;
import ar.edu.itba.sia.game.SkyscrapersState;

public class AdmissibleHeuristic implements Heuristic {
    private final BoardValidator boardValidator;

    public AdmissibleHeuristic(final BoardValidator boardValidator) {
        this.boardValidator = boardValidator;
    }

    @Override
    public Integer getValue(State state) {
        final SkyscrapersState ssState = (SkyscrapersState) state;
        final Board board = ssState.getCurrentBoard();

        return boardValidator.cantConflicts(board);
    }

    public boolean isAdmissible() {
        return false;
    }
}
