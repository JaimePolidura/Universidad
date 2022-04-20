package AlgoritmiaII.entregas.ejercicio2;

import org.testng.collections.Sets;

import java.util.*;

public class ArrayConverter<T extends Comparable<T>> {
    public ArrayList<T> toArrayList(T[] arr, boolean sorted) {
        ArrayList<T> list = new ArrayList<>(Arrays.asList(arr));

        if (sorted) Collections.sort(list);

        return list;
    }

    public LinkedList<T> toLinkedList(T[] arr, boolean sorted) {
        LinkedList<T> list = new LinkedList<>(Arrays.asList(arr));

        return (LinkedList<T>) (sorted ? list.stream().sorted(Comparator.naturalOrder()).toList() : list);
    }

    public Set<T> toSet(T[] arr, boolean sorted) {
        Sets.newHashSet()

        return sorted ? new TreeSet<>(Arrays.asList(arr)) : Sets.newHashSet(arr);
    }

    public Map<T, T> toMap(T[] arr, boolean sorted) {
        Map<T, T> mapToReturn = getPropperMapInstanceToUse(sorted);

        Arrays.stream(arr).forEach(element -> mapToReturn.put(element, element));

        return mapToReturn;
    }

    private Map<T, T> getPropperMapInstanceToUse(boolean sorted){
        return sorted ? new TreeMap<>() : new HashMap<>();
    }
}
