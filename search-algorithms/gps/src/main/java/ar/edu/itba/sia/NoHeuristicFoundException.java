package ar.edu.itba.sia;

public class NoHeuristicFoundException extends RuntimeException {
    public NoHeuristicFoundException(){
        super("Greedy and A star require heuristics");
    }
}
