package ar.edu.itba.sia.game;

import java.awt.Point;

public class Skyscraper {
    private int height;
    private Point position;

    public Skyscraper(int x, int y) {
        this.height = 0;
        this.position = new Point(x, y);
    }

    public Skyscraper(int x, int y,int height) {
        this.height = height;
        this.position = new Point(x, y);
    }


    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }

        if (obj == null){
            return true;
        }

        Skyscraper otherSkyscraper = (Skyscraper) obj;
        return this.position.equals(otherSkyscraper.getPosition())
                && this.height == otherSkyscraper.getHeight();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (height ^ (height >>> 32));
        result = prime * result + (int) (position.getY());
        result = prime * result + (int) (position.getX());
        return result;
    }


    //Getters for the people
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
