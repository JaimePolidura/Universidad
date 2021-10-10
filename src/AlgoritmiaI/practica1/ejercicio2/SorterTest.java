package AlgoritmiaI.practica1.ejercicio2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public final class SorterTest {
    @Test
    public void testBubbleSort() {
        final int[] numbers = { 20, 16, 42, 31, 46, 124 };
        final int[] expected = { 16, 20, 31, 42, 46, 124 };
        final int[] result = Sorter.bubbleSort(numbers);
        assertArrayEquals(expected, result);
    }
}
