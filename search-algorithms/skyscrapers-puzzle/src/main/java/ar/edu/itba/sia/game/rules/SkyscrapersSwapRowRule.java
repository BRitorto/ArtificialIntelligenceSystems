package ar.edu.itba.sia.game.rules;

import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.api.State;
import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.SkyscrapersState;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SkyscrapersSwapRowRule implements Rule {
    public static final int COST = 12;
    private static final String RULE_NAME = "SWAPROW";
    private final String name;
    private int row1;
    private int row2;

    public SkyscrapersSwapRowRule(int row1, int row2) {
        this.row1 = row1;
        this.row2 = row2;
        this.name = RULE_NAME + " (" + row1 + "with" + row2 + ")";
    }

    @Override
    public Integer getCost() {
        return this.COST;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Optional<State> apply(State state) {
        final Board board = ((SkyscrapersState) state).getCurrentBoard();
        Board auxBoard = board.SwapRows(this.row1, this.row2);
        return Optional.of(new SkyscrapersState(auxBoard));
    }
}
