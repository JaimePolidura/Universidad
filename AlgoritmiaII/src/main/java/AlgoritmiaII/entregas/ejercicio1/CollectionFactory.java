package AlgoritmiaII.entregas.ejercicio1;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class CollectionFactory<T extends Comparable<T>> {
    public Collection<T> createAscSortedCollection() {
        return new TreeSet<>();
    }

    public Collection<T> createDescSortedCollection() {
        return new TreeSet<>(Comparator.reverseOrder());
    }
}