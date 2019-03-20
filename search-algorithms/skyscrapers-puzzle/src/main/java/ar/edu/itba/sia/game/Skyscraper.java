package ar.edu.itba.sia.game;

import java.awt.*;

public class Skyscraper {
    private int height;
    private Point position;

    public Skyscraper(int x, int y) {
        this.height = 0;
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }

    public int getHeight() {
        return height;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
