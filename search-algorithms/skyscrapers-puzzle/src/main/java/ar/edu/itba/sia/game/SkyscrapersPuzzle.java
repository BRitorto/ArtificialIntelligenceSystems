package ar.edu.itba.sia.game;

import ar.edu.itba.sia.api.Problem;
import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.api.State;

import java.util.List;

public class SkyscrapersPuzzle implements Problem {
    @Override
    public State getInitState() {
        return null;
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
