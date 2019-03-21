package ar.edu.itba.sia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import ar.edu.itba.sia.game.Board;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class BoardTest {
    private static final int[] dummy_arr = {1,2,3,4,5};
    private int[] topViews, bottomViews, leftViews, rightViews;
    private int dimension;
    @Before
    public void Before() {
        topViews = dummy_arr;
        bottomViews = dummy_arr;
        leftViews = dummy_arr;
        rightViews= dummy_arr;
        dimension = topViews.length;
    }

//    @Test
//    public void mustPassTest(){
//        assertTrue(true);
//    }
    @Test(expected = RuntimeException.class)
    public void wrongSideDimensionsThrowsExceptionTest(){
        int[] bad_side= {1, 2, 3};
        int[] good_side = {1,2,3,4,5};
        int dimension = good_side.length;

        Board fails_board = new Board(dimension, good_side, bad_side, good_side, good_side);
    }

    /*
     *  See getViews implementation:
     *  index   |   view
     *  ------------------
     *     0    |   topViews
     *     1    |   bottomViews
     *     2    |   leftViews
     *     3    |   rightViews
     */
    @Test
    public void createCorrectBoardTest(){

        Board test_board = new Board(this.dimension, this.topViews,
                                    this.bottomViews, this.leftViews, this.rightViews);
        List<int[]> views = test_board.getViews();
        assertArrayEquals(this.topViews, views.get(0));
        assertArrayEquals(this.bottomViews, views.get(1));
        assertArrayEquals(this.leftViews, views.get(2));
        assertArrayEquals(this.rightViews, views.get(3));
    }

    @Test
    public void createBoardFromInitialArrayTest(){
        int[][] initMatrix =
                { {1, 2, 3, 4, 5},
                  {1, 2, 3, 4, 5},
                  {1, 2, 3, 4, 5},
                  {1, 2, 3, 4, 5},
                  {1, 2, 3, 4, 5}};

        Board test_board = new Board(this.dimension, this.topViews, this.bottomViews,
                                    this.leftViews, this.rightViews, initMatrix);

        for(int i = 0; i<this.dimension; i++){
            for(int j=0; j<this.dimension; j++){
                assertEquals(i,test_board.getMatrix()[i][j].getPosition().x);
                assertEquals(j, test_board.getMatrix()[i][j].getPosition().y);
            }
        }




    }
}