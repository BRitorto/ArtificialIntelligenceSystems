package ar.edu.itba.sia.game.rules;

import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.SkyscrapersState;

import java.awt.*;
import java.util.Optional;

public class SkyscrapersSwapColRule implements Rule {
    public static final int COST = 1;
    private static final String RULE_NAME = "SWAPCOL";
    private final String name;

    private int col1;
    private int col2;

    public SkyscrapersSwapColRule(int col1, int col2) {
        this.col1 = col1;
        this.col2 = col2;
        this.name = RULE_NAME + " (" + col1 + "with" + col2 + ")";
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
        Board auxBoard = board.SwapCols(col1, col2);
        return Optional.of(new SkyscrapersState(auxBoard));
    }
}
