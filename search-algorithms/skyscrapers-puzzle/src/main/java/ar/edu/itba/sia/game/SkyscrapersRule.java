package ar.edu.itba.sia.game;

import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.api.State;

import java.awt.*;
import java.util.Optional;

public class SkyscrapersRule implements Rule {
    private int cost;
    private int height;
    private Point position;

    public SkyscrapersRule(int height, int x, int y) {
        this.cost = 1;
        this.height = height;
        this.position = new Point(x, y);
    }

    @Override
    public Integer getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Optional<State> apply(State state) {
        return Optional.empty();
    }
}
