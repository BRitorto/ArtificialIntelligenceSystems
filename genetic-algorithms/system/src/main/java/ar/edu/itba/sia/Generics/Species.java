package ar.edu.itba.sia.Generics;

public interface Species {
    double getFitness();
    double getBoltzmannFitness(double denominator, double temperature);
    Object deepCopy();
}
