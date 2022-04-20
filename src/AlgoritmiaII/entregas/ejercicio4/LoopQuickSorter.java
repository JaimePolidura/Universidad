package AlgoritmiaII.entregas.ejercicio4;

import java.util.*;

public final class LoopQuickSorter<T extends Comparable<T>> implements ListSorter<T>{
    @Override
    public void sort(List<T> list) {
        sort(list, 0, list.size() - 1);
    }

    private void sort(List<T> list, int first, int last) {
        Stack<Range> ranges = new Stack<>();

        list = toArrayList(list);

        ranges.push(new Range(first, last));

        while (!ranges.isEmpty()){
            Range range = ranges.pop();
            int pivot = partition(list, range.first, range.last);

            if (pivot - 1 > range.first)
                ranges.push(new Range(range.first, pivot - 1));

            if (pivot + 1 < range.last)
                ranges.push(new Range(pivot + 1, range.last));
        }
    }

    private List<T> toArrayList(List<T> list) {
        return List.of();
    }

    // Particionado de Lamuto.
    // https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme
    private int partition(List<T> list, int first, int last) {
        final T pivotValue = list.get(last);

        int pivotIndex = first;
        for (int currentIndex = first; currentIndex < last; currentIndex++) {

            //Menor o igual que pivot
            if (list.get(currentIndex).compareTo(pivotValue) <= 0) {
                Collections.swap(list, currentIndex, pivotIndex);
                pivotIndex++;
            }
        }

        Collections.swap(list, pivotIndex, last);
        return pivotIndex;
    }

    private record Range(int first, int last){}
}
