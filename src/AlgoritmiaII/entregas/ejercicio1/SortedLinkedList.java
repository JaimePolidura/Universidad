package AlgoritmiaII.entregas.ejercicio1;

import java.util.Comparator;
import java.util.LinkedList;

public class SortedLinkedList<E extends Comparable<E>> extends LinkedList<E> {
    private final Comparator<E> comparator;

    public SortedLinkedList(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public SortedLinkedList() {
        this.comparator = Comparator.naturalOrder();
    }

    @Override
    public boolean add(E e) {
        int indexItem = findIndexForItem(e);

        super.add(indexItem, e);

        return super.contains(e);
    }

    private int findIndexForItem(E e) {
        if(super.size() == 0) return 0;
        if(super.size() == 1) return super.get(0).compareTo(e) > 0 ? 0 : 1;

        for (int i = 0; i < this.size(); i++) {
            if(comparator.compare(super.get(i), e) > 0){
                return i;
            }
        }

        return -1;
    }
}
