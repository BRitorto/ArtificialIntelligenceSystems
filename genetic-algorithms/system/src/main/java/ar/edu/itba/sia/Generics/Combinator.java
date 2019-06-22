package ar.edu.itba.sia.Generics;

import java.util.List;

public interface Combinator<T extends Species> {
    Couple<T> breed(Couple<T> parents);

    boolean shouldBreed(Couple<T> parents, long generation);

    List<Couple<T>> pickCouples(List<T> potentialParents, int couples);

    String getClassName();
}
