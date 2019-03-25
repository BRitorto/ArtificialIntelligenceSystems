package ar.edu.itba.sia.game;

import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.api.State;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SkyscrapersSwapRule implements Rule {
    public static final int COST = 12;
    private static final String RULE_NAME = "SWAP";
    private final String name;

    private Point pos1;
    private Point pos2;

    public SkyscrapersSwapRule(int x1, int y1, int x2, int y2) {
        this.pos1 = new Point(x1, y1);
        this.pos2 = new Point(x2, y2);
        this.name = RULE_NAME + " (" + x1 + ", " + y1 + ") <-> (" + x2 + ", " + y2 + ")";
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

        if (!board.isFull() || !board.isValidSwap(pos1, pos2)) {
            System.out.println(board.isFull());
            return Optional.empty();
        }

        return Optional.of(new SkyscrapersState(board.swapValue(pos1, pos2)));
    }



    public static void main(String args[]) {
        int leftViews[] = {0,0,0};
        int topViews[] = {0, 0, 0};
        int rightViews[]={0,0,0};
        int bottomViews[]={1,2,2};
        int elseViews[] = {0, 0, 0};
        int m[][] = {{2, 1, 3}, {1, 3, 2}, {3, 2, 1}};

//        int leftViews[] = {2,3,2,1};
//        int topViews[] = {2,1,3,2};
//        int rightViews[] = {2,1,2,3};
//        int bottomViews[]={1,3,2,3};
//        int elseViews[] = {0, 0, 0,0};
//        int m[][] = {{0, 0, 0,0}, {0,0,0, 0}, {0,0, 0, 0},{0,0,0,0}};

//        int leftViews[] = {3, 2, 3, 2, 1};
//        int topViews[] = {4, 2, 1, 2, 3};
//        int rightViews[] = {3, 4, 1, 2, 2};
//        int bottomViews[] = {1, 4, 3, 2, 2};
//        int elseViews[] = {0, 0, 0, 0, 0};
//        int m[][] = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

        List<Point> fixedCells = new LinkedList<>();
        fixedCells.add(new Point(2,2));

        Board b = new Board(3, topViews, bottomViews, leftViews, rightViews, m, fixedCells);
        SkyscrapersState s = new SkyscrapersState(b);
        System.out.println("Inicial");
        s.getCurrentBoard().printMatrix();
        SkyscrapersSwapRule r = new SkyscrapersSwapRule(1,1,0,0);

        boolean rta = r.apply(s).isPresent();

        if(!rta){
            System.out.println("NO ANSWER ... ");
        }else{
            System.out.println("RTA");
            SkyscrapersState state = (SkyscrapersState) r.apply(s).get();
            state.getCurrentBoard().printMatrix();
        }

    }
}
