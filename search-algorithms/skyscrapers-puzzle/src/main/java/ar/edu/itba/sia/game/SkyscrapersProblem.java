package ar.edu.itba.sia.game;

import ar.edu.itba.sia.api.Problem;
import ar.edu.itba.sia.api.Rule;
import ar.edu.itba .sia.api.State;

import java.util.List;

public class SkyscrapersProblem implements Problem<Board> {
    private Board initialBoard;

    public SkyscrapersProblem(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews) {
        this.initialBoard = new Board(dimension, topViews, bottomViews, leftViews, rightViews);
    }

    public SkyscrapersProblem(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews,
                              int[][] initialMatrix) {
        this.initialBoard = new Board(dimension, topViews, bottomViews, leftViews, rightViews, initialMatrix);
    }

    @Override
    public State getInitState() {
        return new SkyscrapersState(this.initialBoard);
    }

    @Override
    public boolean isGoal(State state) {
        return false;
    }

    @Override
    public List<Rule> getRules() {
        return null;
    }
}
