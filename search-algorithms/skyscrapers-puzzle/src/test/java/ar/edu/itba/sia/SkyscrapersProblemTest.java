package ar.edu.itba.sia;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.Skyscraper;
import ar.edu.itba.sia.game.SkyscrapersProblem;
import ar.edu.itba.sia.game.SkyscrapersState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SkyscrapersProblemTest {

    @Before
    public void Before(){

    }

    @Test
    public void goalStateIsGoalTest(){
        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,2,3};
        int bottomViews[]={1,2,3};
        int m[][] = {{2, 1, 3}, {1, 3, 2}, {3, 2, 1}};
        Board b = new Board(3, topViews, bottomViews, leftViews, rightViews, m);
        SkyscrapersState s = new SkyscrapersState(b);

        SkyscrapersProblem prob = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, null);
        assertTrue(prob.isGoal(s));

    }

    @Test
    public void repeatedColumnNumbersStateIsNotGoalTest(){
        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,2,3};
        int bottomViews[]={1,2,3};
        int m[][] = {{2, 1, 3}, {2, 3, 2}, {3, 2, 1}};
        Board b = new Board(3, topViews, bottomViews, leftViews, rightViews, m);
        SkyscrapersState s = new SkyscrapersState(b);
        SkyscrapersProblem prob = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, null);
        assertFalse(prob.isGoal(s));
    }

    @Test
    public void incompleteStateIsNotGoalTest(){
        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,2,3};
        int bottomViews[]={1,2,3};
        int m[][] = {{2, 1, 3}, {2, 0, 0}, {3, 2, 1}};
        Board b = new Board(3, topViews, bottomViews, leftViews, rightViews, m);
        SkyscrapersState s = new SkyscrapersState(b);
        SkyscrapersProblem prob = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, null);

        assertFalse(prob.isGoal(s));

    }

    @Test
    public void repeatedRowNumbersStateIsNotGoalTest(){
        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,2,3};
        int bottomViews[]={1,2,3};
        int m[][] = {{2, 2, 3}, {1, 3, 2}, {3, 2, 1}};
        Board b = new Board(3, topViews, bottomViews, leftViews, rightViews, m);
        SkyscrapersState s = new SkyscrapersState(b);
        SkyscrapersProblem prob = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, null);
        assertFalse(prob.isGoal(s));
    }
}

