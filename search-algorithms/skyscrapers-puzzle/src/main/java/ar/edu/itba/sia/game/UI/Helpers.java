package ar.edu.itba.sia.game.UI;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Helpers {
    public static int getDimensions(Scanner scanner) {
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

    public static String getGameMode(Scanner scanner){
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

    public static int[] getRestrictionArray(Scanner inputScanner, int dimension) {
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

}
