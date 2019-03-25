package ar.edu.itba.sia.game;

import java.awt.*;

public class SkyscrapersPuzzle {

    public static void main(String args[]) {
//        int leftViews[] = {0,0,0};
//        int topViews[] = {0, 0, 0};
//        int rightViews[]={0,0,0};
//        int bottomViews[]={1,2,2};
//        int elseViews[] = {0, 0, 0};
//        int m[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

//        int leftViews[] = {2,3,2,1};
//        int topViews[] = {2,1,3,2};
//        int rightViews[] = {2,1,2,3};
//        int bottomViews[]={1,3,2,3};
//        int elseViews[] = {0, 0, 0,0};
//        int m[][] = {{0, 0, 0,0}, {0,0,0, 0}, {0,0, 0, 0},{0,0,0,0}};

        int leftViews[] = {3, 2, 3, 2, 1};
        int topViews[] = {4, 2, 1, 2, 3};
        int rightViews[] = {3, 4, 1, 2, 2};
        int bottomViews[] = {1, 4, 3, 2, 2};
        int elseViews[] = {0, 0, 0, 0, 0};
        int m[][] = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

        Board b = new Board(5, topViews, bottomViews, leftViews, rightViews, m);
        SkyscrapersState s = new SkyscrapersState(b);
        System.out.println("Inicial");
        s.printMatrix(b.getMatrix());
        //SkyscrapersFillRule r = new SkyscrapersFillRule();

    }

    private boolean fullBoard(Board b) {
        for (int i = 0; i < b.getMatrix().length; i++) {
            for (int j = 0; j < b.getMatrix().length; j++) {
                if (b.getMatrix()[i][j].getHeight() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

// inicializar rules y problem
    // depenede de si es swap o put
    // return new engine con incluido la search strategy
}
