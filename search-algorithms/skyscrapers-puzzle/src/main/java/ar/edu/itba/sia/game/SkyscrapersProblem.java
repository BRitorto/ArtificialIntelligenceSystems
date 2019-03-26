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

    //TODO: Ver si hay una forma más eficiente de checkear que no hayan repetidos
    private boolean checkColsTopBottom(SkyscrapersState state, int[] topView, int[] bottomView){
        PriorityQueue<Integer> bottomQueue = new PriorityQueue<>();
        Skyscraper[][] matrix = state.getCurrentBoard().getMatrix();
        int[] seenHeights = new int[topView.length];
        for (int i = 0; i<topView.length; i++) {
            int max = 0, counterSeen = 0;
            for (int j = 0; j<topView.length; j++) {
                int currHeight = matrix[i][j].getHeight();

                //Si esto se cumple, quiere decir que hay 2 alturas iguales en una misma columna
                if (seenHeights[currHeight - 1] != 0){
                    return false;
                }else{
                    seenHeights[currHeight - 1]++;
                }

                if (currHeight > max) {
                    counterSeen++;
                    max = currHeight;
                }
                updateQueueWithVisibleBuildings(bottomQueue, currHeight);
            }
            if (counterSeen != topView[i] || bottomQueue.size() != bottomView[i]){

                return false;
            }
            while(!bottomQueue.isEmpty()){
                bottomQueue.poll();
            }
            for(int k = 0; k < seenHeights.length; k++){
                seenHeights[k]=0;
            }
        }
        return true;
    }

    private boolean checkRowsLeftRight(SkyscrapersState state, int[] leftView, int[] rightView){
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
        Skyscraper[][] matrix = state.getCurrentBoard().getMatrix();
        int[] seenHeights = new int[leftView.length];

        for (int i =0; i<leftView.length; i++){
            int max = 0, counterSeen = 0;
            for (int j = 0; j<leftView.length; j++){

                int currHeight = matrix[i][j].getHeight();
                //Si esto se cumple, quiere decir que hay 2 alturas iguales en una misma columna
                if (seenHeights[currHeight - 1] != 0){
                    return false;
                }else{
                    seenHeights[currHeight - 1]++;
                }

                if(currHeight > max){
                    counterSeen++;
                    max = currHeight;
                }
                rightQueue = updateQueueWithVisibleBuildings(rightQueue, currHeight);
            }
            if (counterSeen != leftView[i] || rightQueue.size() != rightView[i]){
                return false;
            }
            while(!rightQueue.isEmpty()){
                rightQueue.poll();
            }
            for(int k = 0; k < seenHeights.length; k++){
                seenHeights[k]=0;
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
