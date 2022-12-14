package AlgoritmiaII.entregas.ejercicio2;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class ArrayConverterTest {
    private static ArrayConverter<Integer> converter = new ArrayConverter<>();

    private static Integer[] numbers = new Integer[10000];
    private static long toArrayListTotal;
    private static long toSortedArrayListTotal;
    private static long toLinkedListTotal;
    private static long toSortedLinkedListTotal;
    private static long toSetTotal;
    private static long toSortedSetTotal;
    private static long toMapTotal;
    private static long toSortedMapTotal;

    @Before
    public void setUpBeforeClass() throws Exception {
        final Random random = new Random();
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = random.nextInt();
        }
    }

    @AfterAll
    public void tearDownAfterClass() {
        assertTrue(toArrayListTotal < toSortedArrayListTotal);
        assertTrue(toLinkedListTotal < toSortedLinkedListTotal);
        assertTrue(toSetTotal < toSortedSetTotal);
        assertTrue(toMapTotal < toSortedMapTotal);
    }

    @Test
    public void testToArrayList() {
        final BenchmarkResult result = Benchmark.run(() -> {
            final List<Integer> list = converter.toArrayList(numbers, false);
            assertArrayEquals(numbers, list.toArray());
        }, 1000);
        toArrayListTotal = result.getTotalTime();
    }

    @Test
    public void testToArrayListSorted() {
        final BenchmarkResult result = Benchmark.run(() -> {
            final List<Integer> list = converter.toArrayList(numbers, true);
            assertTrue(isSorted(list.toArray()));
        }, 1000);
        toSortedArrayListTotal = result.getTotalTime();
    }

    @Test
    public void testToLinkedList() {
        final BenchmarkResult result = Benchmark.run(() -> {
            final List<Integer> list = converter.toLinkedList(numbers, false);
            assertArrayEquals(numbers, list.toArray());
        }, 1000);
        toLinkedListTotal = result.getTotalTime();
    }

    @Test
    public void testToLinkedListSorted() {
        final BenchmarkResult result = Benchmark.run(() -> {
            final List<Integer> list = converter.toArrayList(numbers, true);
            assertTrue(isSorted(list.toArray()));
        }, 1000);
        toSortedLinkedListTotal = result.getTotalTime();
    }

    @Test
    public void testToSet() {
        final BenchmarkResult result = Benchmark.run(() -> {
            final Set<Integer> set = converter.toSet(numbers, false);
            assertFalse(isSorted(set.toArray()));
        }, 1000);
        toSetTotal = result.getTotalTime();
    }

    @Test
    public void testToSetSorted() {
        final BenchmarkResult result = Benchmark.run(() -> {
            final Set<Integer> set = converter.toSet(numbers, true);
            assertTrue(isSorted(set.toArray()));
        }, 1000);
        toSortedSetTotal = result.getTotalTime();
    }

    @Test
    public void testToMap() {
        final BenchmarkResult result = Benchmark.run(() -> {
            final Map<Integer, Integer> map = converter.toMap(numbers, false);
            assertFalse(isSorted(map.values().toArray()));
        }, 1000);
        toMapTotal = result.getTotalTime();
    }

    @Test
    public void testToMapSorted() {
        final BenchmarkResult result = Benchmark.run(() -> {
            final Map<Integer, Integer> map = converter.toMap(numbers, true);
            assertTrue(isSorted(map.values().toArray()));
        }, 1000);
        toSortedMapTotal = result.getTotalTime();
    }

    private static boolean isSorted(Object[] arr) {
        Integer prev = (Integer)arr[0];
        for (int i = 1; i < arr.length; ++i) {
            Integer next = (Integer)arr[i];
            if (prev.compareTo(next) > 0) {
                return false;
            }
            prev = next;
        }
        return true;
    }

}
