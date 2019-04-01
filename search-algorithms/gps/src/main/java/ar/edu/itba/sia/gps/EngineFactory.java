package ar.edu.itba.sia.gps;

import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.Problem;

public class EngineFactory {

    public static GPSEngine buildEngine(Problem problem, SearchStrategy strategy,
                                        Heuristic heuristic, int level) {
        GPSEngine answer;
        switch (strategy) {
            case BFS:
                answer = new GPSEngine(problem, SearchStrategy.BFS, null);
                break;
            case DFS:
                answer = new GPSEngine(problem, SearchStrategy.DFS, null);
                break;
            case IDDFS:
                answer = new GPSEngine(problem, SearchStrategy.IDDFS, null);
                //answer.setLevel(level);
                break;
            case GREEDY:
                answer = new GPSEngine(problem, SearchStrategy.GREEDY, heuristic);
                break;
            case ASTAR:
                answer = new GPSEngine(problem, SearchStrategy.ASTAR, heuristic);
                break;
            default:
                throw new RuntimeException("Search strategy is not valid");
        }
        return answer;
    }
    // crea un engine con el problema y la estrategia que va a usar
}
