package ar.edu.itba.sia.game;

import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.api.State;

import java.awt.Point;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class SkyscrapersFillRule implements Rule {
    private int cost;
    private int height;
    private Point position;
    private PriorityQueue<Integer> canSeeRight;
    private PriorityQueue<Integer> canSeeBottom;

    public SkyscrapersFillRule(int height, int x, int y) {
        this.cost = 1;
        this.height = height;
        this.position = new Point(x, y);
        this.canSeeRight = new PriorityQueue<>();
        this.canSeeBottom = new PriorityQueue<>();
    }

    @Override
    public Integer getCost() {
        return this.cost;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Optional<State> apply(State state) {
        SkyscrapersState myState = (SkyscrapersState) state;
        List<int[]> view = myState.getCurrentBoard().getViews();

        Point aux=((SkyscrapersState) state).lastPositionToFill();
        if(aux==null || aux.x!=this.position.x || aux.y!=this.position.y){
            return Optional.empty();
        }

        if (!checkRowsLeftRight(myState, view)) {
            return Optional.empty();
        }

        if (!checkColsTopBottom(myState, view)) {
            return Optional.empty();
        }

        Board rta = myState.getCurrentBoard().cloneBoard();
        rta.getMatrix()[this.position.x][this.position.y] = new Skyscraper(this.position.x, this.position.y, this.height);
        return Optional.of(new SkyscrapersState(rta));
    }

    /*private boolean checkRows(SkyscrapersState state) {
        for (int i = 0; i < state.getCurrentBoard().getMatrix().length ; i++) {
            if (state.getCurrentBoard().getMatrix()[i][this.position.y].getHeight() != 0) {
                if (this.height == state.getCurrentBoard().getMatrix()[i][this.position.y].getHeight())
                    return false;
            }
        }
        return true;
    }*/

    private boolean checkRowsLeftRight(SkyscrapersState state, List<int[]> view) {
        int currMaxRow = 0, currNum, RView, LView = 0;
        for (int i = 0; i <= this.position.y; i++) {
            if (i == this.position.y) {
                currNum = this.height;
            } else {
                currNum = state.getCurrentBoard().getMatrix()[this.position.x][i].getHeight();
                if (this.height == currNum) {//casillero no vacio
                    return false;
                }
            }
            if (view.get(2)[this.position.x] != 0) {
                if (currNum > currMaxRow) {
                    currMaxRow = currNum;
                    LView++;
                }
            }
            updateQueueWithVisibleBuildings(canSeeRight, currNum);
        }
        RView = canSeeRight.size();
        if (view.get(3)[this.position.x] != 0) {
            if (this.position.y + 1 == state.getCurrentBoard().getMatrix().length) { //analizo el ultimo numero de la derecha
                if (RView != view.get(3)[this.position.x]) {
                    return false;
                }
            }
        }
        if (view.get(2)[this.position.x] != 0) {
            if ((view.get(2)[this.position.x]) - LView < 0) {
                return false;
            }
            if ((state.getCurrentBoard().getMatrix().length - currMaxRow) < ((view.get(2)[this.position.x]) - LView)) {//get 2 porque estoy analizando las restricciones de la izq
                return false;
            }
        }
        return true;
    }

    private boolean checkColsTopBottom(SkyscrapersState state, List<int[]> view) {
        int currMaxCol = 0, currNum = 0, TView = 0, BView = 0;
        for (int j = 0; j <= this.position.x; j++) {
            if (j == this.position.x) {
                currNum = this.height;
            } else {
                currNum = state.getCurrentBoard().getMatrix()[j][this.position.y].getHeight();
                if (this.height == currNum) {
                    return false;
                }
            }
            if (view.get(0)[this.position.y] != 0) {

                if (currNum > currMaxCol) {
                    currMaxCol = currNum;
                    TView++;
                }
            }
            updateQueueWithVisibleBuildings(canSeeBottom, currNum);
        }
        BView = canSeeBottom.size();
        if (view.get(0)[this.position.y] != 0) {
            if ((view.get(0)[this.position.y]) - TView < 0) {
                return false;
            }
            if ((state.getCurrentBoard().getMatrix().length - currMaxCol) < ((view.get(0)[this.position.y]) - TView)) {//get o porque estoy analizando las restricciones de arriba
                return false;
            }
        }
        if (view.get(1)[this.position.y] != 0) {
            if (this.position.x + 1 == state.getCurrentBoard().getMatrix().length) {
                if (BView != view.get(1)[this.position.y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateQueueWithVisibleBuildings(PriorityQueue<Integer> queue, int currNum) {
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
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
