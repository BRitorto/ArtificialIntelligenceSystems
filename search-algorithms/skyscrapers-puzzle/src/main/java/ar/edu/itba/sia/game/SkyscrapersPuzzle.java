package ar.edu.itba.sia.game;

import ar.edu.itba.sia.EngineFactory;
import ar.edu.itba.sia.GPSEngine;
import ar.edu.itba.sia.SearchStrategy;
import ar.edu.itba.sia.api.Rule;
import ar.edu.itba.sia.game.heuristics.AdmissibleHeuristic;
import ar.edu.itba.sia.game.rules.SkyscrapersFillRule;
import ar.edu.itba.sia.game.rules.SkyscrapersSwapColRule;
import ar.edu.itba.sia.game.rules.SkyscrapersSwapRowRule;

import javax.swing.text.html.Option;
import java.text.ParseException;
import java.util.*;

public class SkyscrapersPuzzle {
    private static final String FILL_MODE = "F";
    private static final String SWAP_MODE = "S";

    private static final int TOP = 0;
    private static final int BOT = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    public static void main(String args[]) {
        int[][] matrix;
        Scanner inputScanner = new Scanner(System.in);
        String gameMode;
        System.out.println("Welcome to the Skyscrapers Puzzle! " +
                            "please enter F to play on Fill Mode or " +
                            "S to play on Swap Mode");

        gameMode = getGameMode(inputScanner);
        
        int dimensions = getDimensions(inputScanner);
        int[] topView = new int[dimensions], bottomView = new int[dimensions];
        int[] rightView = new int[dimensions], leftView = new int[dimensions];

        System.out.println("Now you need to write the top, bottom, left and right restrictions" +
                " separate each number by a space");
        for(int i=0; i < 4; i++){
            switch(i){
                case TOP:
                    System.out.println("Write the top side restrictions:");
                    topView = getRestrictionArray(inputScanner, dimensions);
                    break;
                case BOT:
                    System.out.println("Write the bottom side restrictions:");
                    bottomView = getRestrictionArray(inputScanner, dimensions);
                    break;
                case LEFT:
                    System.out.println("Write the left side restrictions");
                    leftView = getRestrictionArray(inputScanner, dimensions);
                    break;
                case RIGHT:
                    System.out.println("Write the right side restrictions");
                    rightView = getRestrictionArray(inputScanner, dimensions);
                    break;
            }
        }

        SkyscrapersProblem problem;
        EngineFactory factory;
        GPSEngine engine;

        if(gameMode.equals(FILL_MODE)){
            matrix = new int[dimensions][dimensions];
            problem = new SkyscrapersProblem(dimensions, topView, bottomView, leftView, rightView,
                    getFillRules(matrix), matrix);
            factory = new EngineFactory();
            engine = factory.buildEngine(problem, SearchStrategy.GREEDY, new AdmissibleHeuristic(), 0);
            engine.findSolution();
        }else{
            matrix = initializeMatrix(dimensions);
            problem = new SkyscrapersProblem(dimensions, topView, bottomView, leftView, rightView,
                    getSwapRules(matrix), matrix);
            factory = new EngineFactory();
            engine = factory.buildEngine(problem, SearchStrategy.GREEDY, new AdmissibleHeuristic(), 0);
            engine.findSolution();
        }

        System.out.println("Game ended, winning board: ");
        System.out.println(engine.getSolutionNode().getState().getRepresentation());

        inputScanner.close();
    }

    private static int getDimensions(Scanner scanner) {
        System.out.println("Enter the dimension of the board you want to play in" +
                " (it has to be NxN, so just write N)");
        int dimension=0;
        boolean correctInput;
         do {
             if(scanner.hasNextInt()) {
                 dimension = scanner.nextInt();
                 correctInput = true;
             }else{
                 scanner.next(); //"Flush" input.
                 correctInput = false;
                 System.out.println("Wrong input, please remember that the dimension must be a number" +
                                    " and if the board is NxN, just write N");
             }
         }while(!correctInput);

         return dimension;
    }

    //Hacer este método más piola más adelante
    private static int[][] initializeMatrix(int dimensions) {
        int m[][] = {{2,1,4,3},{3,4,1,2},{4,2,3,1},{1,3,2,4}};

        return m;
    }

    private static int[] getRestrictionArray(Scanner inputScanner, int dimension) {
        int[] restriction = new int[dimension];
        String input;
        String[] inputElems;
        boolean correctInput = false;
        do {
            input = inputScanner.nextLine();
            inputElems = input.split(" ");
            // TODO: When entering for the first time, it throws the not a valid input w/o asking for input
            if (inputElems.length != dimension){
                System.out.printf("Not a valid input, remember to write %d numbers divided by spaces\n", dimension);
            }else{
                Optional<Integer[]> restrictionArray = verifyArray(inputElems);
                if(restrictionArray.isPresent()){
                    correctInput=true;
                    // Using and abusing of Java8 new functionalities. My goal is to transform an Integer[] to an
                    // int[]. For more information, see: https://www.techiedelight.com/convert-list-integer-array-int/
                    restriction = Arrays.stream(restrictionArray.get()).mapToInt(Integer::intValue).toArray();
                }
            }

        }while(!correctInput);

        return restriction;

    }

    private static Optional<Integer[]> verifyArray(String[] inputElems) {
        Integer[] verifiedArray = new Integer[inputElems.length];
        int counter=0;
        for( String element : inputElems){
            try{
                verifiedArray[counter] = Integer.parseInt(element);
            }catch(NumberFormatException e){
                System.out.println("Here");
                System.out.println("Not a valid input, remember that you have to write NUMBERS divided by spaces");
                return Optional.empty();
            }
            if(verifiedArray[counter] > inputElems.length){
                System.out.println("Not a valid input, the numbers can't be bigger than the boards dimensions");
                return Optional.empty();
            }else{
                counter++;
            }
        }
        return Optional.of(verifiedArray);
    }

    private static String getGameMode(Scanner scanner){
        String gameMode;
        boolean correctInput = false;
        do{

            gameMode = scanner.next();
            if (!(gameMode.equals("F") || gameMode.equals("S"))){
                System.out.println("Wrong Input, please remember to enter F or S");
            }else{
                correctInput=true;
            }
        }while(!correctInput);

        return gameMode;
    }

    public static List<Rule> getFillRules(int[][] m) {
        LinkedList rules = new LinkedList<SkyscrapersFillRule>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                for (int k = 1; k <= 4; k++){
                    SkyscrapersFillRule rule = new SkyscrapersFillRule(k, i, j);
                    rules.add(rule);
                }
            }
        }
        return rules;
    }

    public static List<Rule> getSwapRules(int[][] m) {
        LinkedList rules = new LinkedList<Rule>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < i; j++) {
                SkyscrapersSwapRowRule ruleRow = new SkyscrapersSwapRowRule(i, j);
                SkyscrapersSwapColRule ruleCol = new SkyscrapersSwapColRule(i, j);
                rules.add(ruleRow);
                rules.add(ruleCol);
            }
        }
        return rules;
    }

    private void garbage(){
        int topViews[] = {2,1,3,2};
        int bottomViews[]={1,3,2,3};
        int leftViews[] = {2,3,2,1};
        int rightViews[] = {2,1,2,3};
        int m[][] = {{2,1,4,3},{3,4,1,2},{4,2,3,1},{1,3,2,4}};
    }
}