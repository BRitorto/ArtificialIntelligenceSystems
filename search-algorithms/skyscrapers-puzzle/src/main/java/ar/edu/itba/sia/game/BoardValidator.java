package ar.edu.itba.sia.game;

import java.util.PriorityQueue;

public class BoardValidator {

    /*public static void main(String[] args){
        int leftViews[] = {1,0,2};
        int topViews[] = {0,0,3};
        int rightViews[] = {3,2,1};
        int bottomViews[] = {0,2,1};
        int m[][] = {{2,2,1}, {1,3,2}, {2,1,3}};
        Board b=new Board(3,topViews,bottomViews,leftViews,rightViews,m);
        BoardValidator validator=new BoardValidator();

        System.out.println(validator.cantConflicts(b));
    }*/

    public BoardValidator(){

    }

    public int cantConflicts(Board b){
        Skyscraper[][] matrix=b.getMatrix();
        int cantConflicts=0;
        int[] topView=b.getTopViews();
        int[] bottomView=b.getBottomViews();
        int[] leftView=b.getLeftViews();
        int[] rightView=b.getRightViews();

        cantConflicts+=checkColsTopBottom(matrix,topView,bottomView);
        cantConflicts+=checkRowsLeftRight(matrix,leftView,rightView);

        return cantConflicts;

    }

    private int checkColsTopBottom(Skyscraper[][] matrix, int[] topView, int[] bottomView){
        PriorityQueue<Integer> bottomQueue = new PriorityQueue<>();
        int[] seenHeights = new int[topView.length];
        int cantConflicts=0;

        for (int j = 0; j<topView.length; j++) {
            int max = 0, counterSeen = 0;
            for (int i = 0; i<topView.length; i++) {
                int currHeight = matrix[i][j].getHeight();
                if (currHeight == 0)

                //Si esto se cumple, quiere decir que hay 2 alturas iguales en una misma columna
                if (seenHeights[currHeight - 1] != 0){
                    cantConflicts++;
                    break;
                }else{
                    seenHeights[currHeight - 1]++;
                }

                if (currHeight > max) {
                    counterSeen++;
                    max = currHeight;
                }
                updateQueueWithVisibleBuildings(bottomQueue, currHeight);
            }
            if(bottomView[j] != 0){
                if(bottomQueue.size() != bottomView[j]){
                    cantConflicts++;
                    break;
                }
            }
            if( topView[j] != 0) {
                if (counterSeen != topView[j] ) {
                    cantConflicts++;
                    break;
                }
            }
            while(!bottomQueue.isEmpty()){
                bottomQueue.poll();
            }
            for(int k = 0; k < seenHeights.length; k++){
                seenHeights[k]=0;
            }
        }
        return cantConflicts;
    }

    private int checkRowsLeftRight(Skyscraper[][] matrix, int[] leftView, int[] rightView){
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
        int[] seenHeights = new int[leftView.length];
        int cantConflicts=0;

        for (int i =0; i<leftView.length; i++){
            int max = 0, counterSeen = 0;
            for (int j = 0; j<leftView.length; j++){

                int currHeight = matrix[i][j].getHeight();
                //Si esto se cumple, quiere decir que hay 2 alturas iguales en una misma columna
                if (seenHeights[currHeight - 1] != 0){
                    cantConflicts++;
                    break;
                }else{
                    seenHeights[currHeight - 1]++;
                }

                if(currHeight > max){
                    counterSeen++;
                    max = currHeight;
                }
                rightQueue = updateQueueWithVisibleBuildings(rightQueue, currHeight);
            }
            if(rightView[i] !=0){
                if(rightQueue.size() != rightView[i]){
                    cantConflicts++;
                    break;
                }
            }
            if(leftView[i] != 0  ) {
                if (counterSeen != leftView[i]  ) {
                    cantConflicts++;
                    break;
                }
            }
            while(!rightQueue.isEmpty()){
                rightQueue.poll();
            }
            for(int k = 0; k < seenHeights.length; k++){
                seenHeights[k]=0;
            }
        }
        return cantConflicts;
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
}
