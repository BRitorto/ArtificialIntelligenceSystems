package ar.edu.itba.sia;

import ar.edu.itba.sia.game.Board;
import ar.edu.itba.sia.game.Skyscraper;
import ar.edu.itba.sia.game.SkyscrapersProblem;
import ar.edu.itba.sia.game.SkyscrapersState;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class SkyscrapersProblemTest {

    @Before
    public void Before(){

    }

    @Test
    public void GoalStateIsGoalTest(){
        Skyscraper[][] matrix = new Skyscraper[3][3];
        int leftViews[] = {2,2,1};
        int topViews[] = {2, 2, 1};
        int rightViews[]={1,2,3};
        int bottomViews[]={1,2,3};
        int m[][] = {{2, 1, 3}, {1, 3, 2}, {3, 2, 1}};
        Board b = new Board(3, topViews, bottomViews, leftViews, rightViews, m);
        SkyscrapersState s = new SkyscrapersState(b);

        SkyscrapersProblem prob = new SkyscrapersProblem(3, topViews, bottomViews, leftViews, rightViews, null);
        //assertTrue(prob.isGoal(s));
        System.out.println(prob.isGoal(s));



    }
}

