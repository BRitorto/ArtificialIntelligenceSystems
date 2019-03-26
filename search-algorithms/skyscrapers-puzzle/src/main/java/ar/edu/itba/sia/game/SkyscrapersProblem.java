package ar.edu.itba.sia.game;

import ar.edu.itba.sia.api.Problem;
import ar.edu.itba.sia.api.Rule;
import ar.edu.itba .sia.api.State;

import java.util.List;
import java.util.PriorityQueue;

public class SkyscrapersProblem implements Problem<Board> {
    private Board initialBoard;
    private List<Rule> rules;

    public SkyscrapersProblem(int dimension, int[] topViews, int[] bottomViews, int[] leftViews, int[] rightViews,
                              List<Rule> rules) {
        this.initialBoard = new Board(dimension, topViews, bottomViews, leftViews, rightViews);
        this.rules = rules;
    }

    @Override
    public State getInitState() {
        return new SkyscrapersState(this.initialBoard);
    }

    @Override
    public boolean isGoal(State state) {
        SkyscrapersState currState = (SkyscrapersState) state;
        int index = 0, dimension = currState.getCurrentBoard().getMatrix().length;
        int[] topView = new int[dimension];
        int[] bottomView = new int[dimension];
        int[] leftView = new int[dimension];
        int[] rightView = new int[dimension];

        for(int[] view: currState.getCurrentBoard().getViews()){
            switch(index){
                case 0:
                    topView = view;
                    break;
                case 1:
                    bottomView = view;
                    break;
                case 2:
                    leftView = view;
                    break;
                case 3:
                    rightView = view;
                    break;
            }
            index++;
        }

        if (!checkColsTopBottom(currState,topView, bottomView)){
            return false;
        }
        if (!checkRowsLeftRight(currState, leftView, rightView)) {
            return false;
        }
        return true;

    }

    private boolean checkColsTopBottom(SkyscrapersState state, int[] topView, int[] bottomView){
        PriorityQueue<Integer> bottomQueue = new PriorityQueue<>();
        Skyscraper[][] matrix = state.getCurrentBoard().getMatrix();
        for (int i = 0; i<topView.length; i++) {
            int max = 0, counterSeen = 0;
            for (int j = 0; j<topView.length; j++) {
                int curr_height = matrix[i][j].getHeight();
                if (curr_height > max) {
                    counterSeen++;
                    max = curr_height;
                }
                updateQueueWithVisibleBuildings(bottomQueue, curr_height);
            }
            if (counterSeen != topView[i] || bottomQueue.size() != bottomView[i]){
                return false;
            }
            while(!bottomQueue.isEmpty()){
                bottomQueue.poll();
            }
        }
        return true;
    }

    private boolean checkRowsLeftRight(SkyscrapersState state, int[] leftView, int[] rightView){
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
        Skyscraper[][] matrix = state.getCurrentBoard().getMatrix();
        for (int i =0; i<leftView.length; i++){
            int max = 0, counterSeen = 0;
            for (int j = 0; j<leftView.length; j++){
                int curr_height = matrix[i][j].getHeight();
                if(curr_height > max){
                    counterSeen++;
                    max = curr_height;
                }
                rightQueue = updateQueueWithVisibleBuildings(rightQueue, curr_height);
            }
            if (counterSeen != leftView[i] || rightQueue.size() != rightView[i]){
                return false;
            }
            while(!rightQueue.isEmpty()){
                rightQueue.poll();
            }
        }
        return true;
    }

    private PriorityQueue<Integer> updateQueueWithVisibleBuildings(PriorityQueue<Integer> queue, int currNum) {
        boolean end_cond = false;
        do {
            if (queue.isEmpty()) {
                end_cond = true;
                queue.offer(currNum);
            } else {
                if (queue.peek() < currNum) {
                    queue.poll();
                } else {
                    queue.offer(currNum);
                    end_cond = true;
                }
            }
        } while (!end_cond);
        return queue;
    }



    @Override
    public List<Rule> getRules() {
        return rules;
    }
}
