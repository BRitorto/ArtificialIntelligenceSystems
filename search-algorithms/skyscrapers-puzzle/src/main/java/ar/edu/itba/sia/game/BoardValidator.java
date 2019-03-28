package ar.edu.itba.sia.game;

import java.util.PriorityQueue;

public class BoardValidator {

    public static void main(String[] args){
        int leftViews[] = {1,0,0};
        int topViews[] = {3,2,2};
        int rightViews[] = {3,2,1};
        int bottomViews[] = {0,1,1};
        int m[][] = {{3,3,3},{3,3,3}, {3,3,3}};
        Board b=new Board(3,topViews,bottomViews,leftViews,rightViews,m);
        BoardValidator validator=new BoardValidator();

        System.out.println(validator.cantConflicts(b));
    }

    public BoardValidator(){

    }

    public int cantConflicts(Board b){
        Skyscraper[][] matrix=b.getMatrix();
        int cantConflicts=0;
        int[] topView=b.getTopViews();
        int[] bottomView=b.getBottomViews();
        int[] leftView=b.getLeftViews();
        int[] rightView=b.getRightViews();

        int repetidos=checkRowsCols(b);
        int rows=checkRowsLeftRight(b.getMatrix(),leftView,rightView);
        int cols=checkColsTopBottom(b.getMatrix(),topView,bottomView);

        System.out.println("Cant repetidos:"+repetidos);
        System.out.println("Cant mal rows:"+rows);
        System.out.println("Cant mal cols:"+cols);
        return repetidos+cols+rows;

    }

    //por cada numero con conflictos de repetidos en la fila o la columna, sumo 1.
    //Maxima cantidad de conflictos: NxN
    private int checkRowsCols(Board b){
        int lenght=b.getMatrix().length;
        int currNum=0;
        int cantConflicts=0;
        boolean end=false;
        for(int i=0;i<lenght;i++){
            for(int j=0;j<lenght;j++){
                end=false;
                currNum=b.getMatrix()[i][j].getHeight();
                for(int m=0;m<lenght ;m++){//chequeo fila
                    if(m!=j) {
                        if (currNum == b.getMatrix()[i][m].getHeight()) {
                            cantConflicts++;
                            end = true; //ya encontre conflicto para ese numero
                            break;
                        }
                    }
                }
                for(int m=0;m<lenght && !end;m++){//chequeo columna
                    if(m!=i) {
                        if (currNum == b.getMatrix()[m][j].getHeight()) {
                            cantConflicts++;
                            end = true;
                            break;
                        }
                    }
                }
            }
        }
        return cantConflicts;
    }

    //por cada fila que este mal, puedo sumar 1 por la izquierda y 1 por la derecha. Por cada columna que este mal, puedo sumar 1 por arriba y 1 por abajo
    //Maxima cantidad de conflictos: (fila+columas)*2
    private int checkColsTopBottom(Skyscraper[][] matrix, int[] topView, int[] bottomView){
        PriorityQueue<Integer> bottomQueue = new PriorityQueue<>();
        int[] seenHeights = new int[topView.length];
        int cantConflicts=0;

        for (int j = 0; j<topView.length; j++) {
            int max = 0, counterSeen = 0;
            for (int i = 0; i<topView.length; i++) {
                int currHeight = matrix[i][j].getHeight();

                if (currHeight > max) {
                    counterSeen++;
                    max = currHeight;
                }
                if(bottomView[j]!=0) {
                    updateQueueWithVisibleBuildings(bottomQueue, currHeight);
                }
            }
            if(bottomView[j] != 0){
                if(bottomQueue.size() != bottomView[j]){
                    cantConflicts++;
                }
            }
            if( topView[j] != 0 ) {
                if (counterSeen != topView[j] ) {
                    cantConflicts++;

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

                if(currHeight > max){
                    counterSeen++;
                    max = currHeight;
                }
                if(rightView[i] !=0) {
                    updateQueueWithVisibleBuildings(rightQueue, currHeight);
                }
            }

            if(rightView[i] !=0){
                if(rightQueue.size() != rightView[i]){
                    cantConflicts++;
                }
            }
            if(leftView[i] != 0  ) {
                if (counterSeen != leftView[i]  ) {
                    cantConflicts++;
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

    private void updateQueueWithVisibleBuildings(PriorityQueue<Integer> queue, int currNum) {
        boolean end_cond = false;
        do {
            if (queue.isEmpty()) {
                end_cond = true;
                queue.offer(currNum);
            } else {
                if (queue.peek() <= currNum) {
                    queue.poll();
                } else {
                    queue.offer(currNum);
                    end_cond = true;
                }
            }
        } while (!end_cond);
        return;
    }
}
