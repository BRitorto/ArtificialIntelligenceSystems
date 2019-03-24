package ar.edu.itba.sia.game;

import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.api.State;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class SkyscrapersRule implements Rule {
    private int cost;
    private int height;
    private Point position;

    public SkyscrapersRule(int height, int x, int y) {
        this.cost = 1;
        this.height = height;
        this.position = new Point(x, y);
    }

    public SkyscrapersRule() {

    }

    @Override
    public Integer getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Optional<State> apply(State state) {
        return Optional.empty();
    }


    public Board applyNumber(int addNum, Point pos, Board board) {
        int currMaxRow = 0, currMaxCol = 0, minRView = board.getMatrix().length, RView = 0, LView = 0, currNum = 0, TView = 0, BView = 0;//tView:topView

        PriorityQueue<Integer> canSeeRight = new PriorityQueue<>();
        PriorityQueue<Integer> cannSeeBottom = new PriorityQueue<>();
        List<int[]> view = board.getViews();

        //chequeo filas
        for (int i = 0; i <= pos.y; i++) {
            if (i == pos.y) {
                currNum = addNum;
            } else {
                currNum = board.getMatrix()[pos.x][i].getHeight();
                if (addNum == currNum) {//casillero no vacio
                    return null;
                }
            }
            if (view.get(2)[pos.x] != 0) {
                if (currNum > currMaxRow) {
                    currMaxRow = currNum;

                    LView++;
                }
            }

            updateQueueWithVisibleBuildings(canSeeRight, currNum);
        }
        RView = canSeeRight.size();

        //chequeo cols
        for (int j = 0; j <= pos.x; j++) {
            if (j == pos.x) {
                currNum = addNum;
            } else {
                currNum = board.getMatrix()[j][pos.y].getHeight();
                if (addNum == currNum) {//casillero no vacio
                    return null;
                }
            }

            //si la restriccion es distinta de cero analizo, sino no hace falta
            if (view.get(0)[pos.y] != 0) {

                if (currNum > currMaxCol) {
                    currMaxCol = currNum;

                    TView++;
                }
            }
            updateQueueWithVisibleBuildings(cannSeeBottom, currNum);

        }
        BView = cannSeeBottom.size();


        //analizo res de la izq
        if (view.get(2)[pos.x] != 0) {
            if ((view.get(2)[pos.x]) - LView < 0) {
                return null;
            }
            if ((board.getMatrix().length - currMaxRow) < ((view.get(2)[pos.x]) - LView)) {//get 2 porque estoy analizando las restricciones de la izq
                return null;
            }
        }

        //analizo res de arriba
        if (view.get(0)[pos.y] != 0) {

            if ((view.get(0)[pos.y]) - TView < 0) {
                return null;
            }
            if ((board.getMatrix().length - currMaxCol) < ((view.get(0)[pos.y]) - TView)) {//get o porque estoy analizando las restricciones de arriba
                return null;
            }
        }

        //anallizo restriccion de la derecha
//        if (view.get(3)[pos.x] != 0) {
//
//            if ((RView + (board.getMatrix().length - (pos.x + 1))) != (view.get(3)[pos.x])) {
//                return null;
//            }
//        }

        if (view.get(3)[pos.x] != 0) {
            if (pos.y + 1 == board.getMatrix().length) { //analizo el ultimo numero de la derecha
                if (RView != view.get(3)[pos.x]) {
                    return null;
                }
//            } else {
//                if (RView > view.get(3)[pos.x]) {
//                    return null;
//                }
            }
        }

        //analizo bottom
        if (view.get(1)[pos.y] != 0) {
            if (pos.x + 1 == board.getMatrix().length) { //analizo el ultimo numero de la derecha
                if (BView != view.get(1)[pos.y]) {
                    return null;
                }
//            } else {
//                if (RView > view.get(3)[pos.x]) {
//                    return null;
//                }
            }
        }

        Board rta = board.cloneBoard();

        rta.getMatrix()[pos.x][pos.y] = new Skyscraper(pos.x, pos.y, addNum);

        if (fullBoard(rta)) {
            System.out.println("Nueva board:");

            rta.printMatrix();
        }


        return rta;
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

    private Board testRec(Board b) {
        return rec(b, new Point(0, 0), 0);
    }

    private Board rec(Board b, Point p, int add) {
        Board curr = b;
        Board r1 = b;
        Board r2 = b;
        if (p.y == b.getMatrix().length && p.x == b.getMatrix().length + 1) {// NOSE PORQUE PERO SINO NO ME ANDA
            return b;
        } else {
            for (int i = 1; i <= b.getMatrix().length; i++) {
                curr = applyNumber(i, p, b);

                if (curr != null) {
                    if (p.y + 1 < b.getMatrix().length) {
                        r1 = rec(curr, new Point(p.x, p.y + 1), i);
                    }
                    if (r1 != null) {
                        if (p.x + 1 < b.getMatrix().length) {
                            r2 = rec(r1, new Point(p.x + 1, 0), i);
                        }
                    }
                }
            }
            if (curr == null) {
                return null;
            }
            if (r1 == null) {
                return null;
            }
            if (r2 == null) {
                return null;
            }
        }
        return r2;
    }

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
        SkyscrapersRule r = new SkyscrapersRule();

        Board rta = r.testRec(b);

        if( rta == null){
            System.out.println("NO ANSWER ... ");
        }else{
            System.out.println("RTA");
            rta.printMatrix();
        }

    }
}
