package AlgoritmiaII.entregas.ejercicio4;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoopQuickSorterTest {
    final Integer[] expected = {1, 2, 3, 4, 5};
    final List<Integer> unsortedValues = List.of(new Integer[]{5, 4, 1, 2, 3});

    @Test
    public void shouldSortArrayList() {
        List<Integer> list = new ArrayList<>(unsortedValues);
        new LoopQuickSorter<Integer>().sort(list);
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void shouldSortLinkedList() {
        List<Integer> list = new LinkedList<>(unsortedValues);
        new LoopQuickSorter<Integer>().sort(list);
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testSameOrderComplexity() {
        List<Integer> sourceList = getRandomValues(1024);
        LoopQuickSorter<Integer> loopQuickSorter = new LoopQuickSorter<>();

        ArrayList<Integer> arrayList = new ArrayList<>(sourceList);
        BenchmarkResult arrayListResult = Benchmark.run(() -> loopQuickSorter.sort(arrayList), 1);

        LinkedList<Integer> linkedList = new LinkedList<>(sourceList);
        BenchmarkResult linkedListResult = Benchmark.run(() -> loopQuickSorter.sort(linkedList), 1);

        System.out.printf("LinkedList (ms): %d\n", linkedListResult.getMax());
        System.out.printf("ArrayList (ms): %d\n", arrayListResult.getMax());

        assertTrue(linkedListResult.getAverage() / arrayListResult.getAverage() < 2d);
    }

    private List<Integer> getRandomValues(int n) {
        final Random rnd = new Random();
        List<Integer> list = new ArrayList<>(n);

        for (int i = 0; i < n; ++i) {
            list.add(rnd.nextInt());
        }

        return list;
    }

}
