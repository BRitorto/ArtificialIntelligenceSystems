package ar.edu.itba.sia.game.rules;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.Skyscraper;
import ar.edu.itba.sia.game.SkyscrapersState;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;

import java.awt.*;
import java.util.Optional;

public class SkyscrapersSwapRule implements Rule {
    public static final int COST = 1;
    private static final String RULE_NAME = "SWAP";
    private final String name;

    private final int x;
    private final int y1;
    private final int y2;

    public SkyscrapersSwapRule(final Point point1, final Point point2) {
        this.x = (int) point1.getX();
        this.y1 = (int) point1.getY();
        this.y2 = (int) point2.getY();
        this.name = RULE_NAME + " (" + x + ", " + y1 + ") <-> (" + x + ", " + y2 + ")";
    }
    @Override
    public Integer getCost() {
        return null;
    }

    @Override
    public String getName() {
        return "\nRule: " + name + "\n\n";
    }

    @Override
    public Optional<State> apply(State state) {
        final Board board = ((SkyscrapersState) state).getCurrentBoard();
        if (!board.isComplete()) {
            return Optional.empty();
        }
        Board auxBoard = swapValue(board, x, y1, y2);
        return Optional.of(new SkyscrapersState(auxBoard));
    }

    private Board swapValue(Board board, int x, int y1, int y2) {
        Board auxBoard = board.cloneBoard();
        Skyscraper aux = auxBoard.getMatrix()[x][y1];
        auxBoard.getMatrix()[x][y1] = auxBoard.getMatrix()[x][y2];
        auxBoard.getMatrix()[x][y2] = aux;
        return auxBoard;
    }
}
