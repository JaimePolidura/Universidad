package AlgoritmiaII.entregas.ejercicio5;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public final class IntListFuncsTests {
    @Test
    public void testCountUpDownFromZero() {
        final Integer[] expected = {0, 1, 2, 3, 2, 1, 0};
        assertArrayEquals(expected, IntListFuncs.countUpDown(0, 3).toArray());

    }

    @Test
    public void testCountUpDownFromNonZero() {
        final Integer[] expected = {3, 4, 5, 6, 5, 4, 3};
        assertArrayEquals(expected, IntListFuncs.countUpDown(3, 6).toArray());
    }

    @Test
    public void testCountUpDownFromZeroToZero() {
        final Integer[] expected = {0};
        assertArrayEquals(expected, IntListFuncs.countUpDown(0, 0).toArray());
    }

    @Test
    public void testCountUpDownWithFromGreaterValue() {
        final Integer[] expected = {0, 1, 2, 3, 2, 1, 0};
        assertArrayEquals(expected, IntListFuncs.countUpDown(0, 3).toArray());
    }

    @Test
    public void testSplitEach2ForOddListCount() {
        final List<Integer> input = List.of(1, 2, 3, 4, 5, 6);
        final List<List<Integer>> expected = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5, 6)
        );
        assertEquals(expected, IntListFuncs.splitEach(input, 2));
    }

    @Test
    public void testSplitEach3ForOddListCount() {
        final List<Integer> input = List.of(1, 2, 3, 4, 5, 6);
        final List<List<Integer>> expected = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6)
        );
        assertEquals(expected, IntListFuncs.splitEach(input, 3));
    }

    @Test
    public void testSplitEach2ForEvenListCount() {
        final List<Integer> input = List.of(1, 2, 3, 4, 5);
        final List<List<Integer>> expected = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5)
        );
        assertEquals(expected, IntListFuncs.splitEach(input, 2));
    }

    @Test
    public void testSplitEach3ForEvenListCount() {
        final List<Integer> input = List.of(1, 2, 3, 4, 5);
        final List<List<Integer>> expected = List.of(
                List.of(1, 2, 3),
                List.of(4, 5)
        );
        assertEquals(expected, IntListFuncs.splitEach(input, 3));
    }

    @Test
    public void testParityBitSumOfEmpty() {
        final List<Integer> input = List.of();
        final int expected = 0;
        assertEquals(expected, IntListFuncs.parityBitSum(input));
    }

    @Test
    public void testParityBitSumOfOneItem() {
        final List<Integer> input = List.of(10101);
        final int expected = 3;

        assertEquals(expected, IntListFuncs.parityBitSum(input));
    }

    @Test
    public void testParityBitSumOfItems() {
        final List<Integer> input = List.of(
                1100,
                11,
                110,
                1001,
                1111
        );

        final int expected = 2 + 2 + 2 + 2 + 4;

        assertEquals(IntListFuncs.parityBitSum(input), expected);
    }
}
