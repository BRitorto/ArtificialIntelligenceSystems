package ar.edu.itba.sia;

import ar.edu.itba.sia.factories.BoardFactory;
import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.Skyscraper;
import ar.edu.itba.sia.game.SkyscrapersProblem;
import ar.edu.itba.sia.game.SkyscrapersState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SkyscrapersProblemTest {

    @Test
    public void goalStateIsGoalTest(){
        Board b = BoardFactory.createCorrectBoard();
        SkyscrapersState s = new SkyscrapersState(b);
        SkyscrapersProblem prob = new SkyscrapersProblem(b, null);

        assertTrue(prob.isGoal(s));

    }

    @Test
    public void repeatedColumnNumbersStateIsNotGoalTest(){
        int m[][] = {{2, 1, 3}, {2, 3, 2}, {3, 2, 1}};
        Board b = BoardFactory.createWithCustomMatrix(m);
        SkyscrapersState s = new SkyscrapersState(b);
        SkyscrapersProblem prob = new SkyscrapersProblem(b, null);

        assertFalse(prob.isGoal(s));
    }

    @Test
    public void repeatedRowNumbersStateIsNotGoalTest(){
        int m[][] = {{2, 2, 3}, {1, 3, 2}, {3, 2, 1}};
        Board b = BoardFactory.createWithCustomMatrix(m);
        SkyscrapersState s = new SkyscrapersState(b);
        SkyscrapersProblem prob = new SkyscrapersProblem(b, null);

        assertFalse(prob.isGoal(s));
    }

    @Test
    public void incompleteStateIsNotGoalTest(){
        Board b = BoardFactory.createIncompleteBoard();
        SkyscrapersState s = new SkyscrapersState(b);
        SkyscrapersProblem prob = new SkyscrapersProblem(b, null);

        assertFalse(prob.isGoal(s));

    }

    @Test
    public void zeroOnConstraintMeansNoConstraintTest(){
        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,0,3};
        int bottomViews[]={0,0,0};
        int m[][] = {{2, 1, 3}, {1, 3, 2}, {3, 2, 1}};
        Board b = new Board(3, topViews, bottomViews, leftViews, rightViews, m);
        SkyscrapersState s = new SkyscrapersState(b);

        SkyscrapersProblem prob = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, null);
        assertTrue(prob.isGoal(s));

    }

    @Test
    public void isItBrokenTest(){
        int leftViews[] = {0, 0, 0,0};
        int topViews[] = {2,1,3,2};
        int rightViews[] = {2,1,2,3};
        int bottomViews[]={1,3,2,3};
        int m[][] = {{3,4,1,2}, {1,3,2,4}, {2,1,4,3},{4,2,3,1}};
        Board b = new Board(4, topViews, bottomViews, leftViews, rightViews, m);
        SkyscrapersState s = new SkyscrapersState(b);

        SkyscrapersProblem prob = new SkyscrapersProblem(4, topViews, bottomViews, leftViews, rightViews, null);
        assertTrue(prob.isGoal(s));
    }
}

