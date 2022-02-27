package AlgoritmiaII.entregas.ejercicio1;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class CollectionFactory<T extends Comparable<T>> {
    public Collection<T> createAscSortedCollection() {
        //return new TreeSet<>();
        return new SortedLinkedList<>(comparator);
    }

    public Collection<T> createDescSortedCollection() {
        return new TreeSet<>(Comparator.reverseOrder());
    }
}
