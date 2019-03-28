package ar.edu.itba.sia;

import ar.edu.itba.sia.GPSEngine;

import java.util.Objects;

public class GenericTests {

    public static void solutionFound(GPSEngine engine){
        assert engine.isFinished() : String.format("After findSolution, the finished flag was not set, with %s strategy", engine.getStrategy().name());
        assert !engine.isFailed() : String.format("After findSolution with a no solution problem, the finished flag was not set, with %s strategy", engine.getStrategy().name());
        assert Objects.nonNull(engine.getSolutionNode()) : "After findSolution, engine ended but solution node is null";
    }

}
