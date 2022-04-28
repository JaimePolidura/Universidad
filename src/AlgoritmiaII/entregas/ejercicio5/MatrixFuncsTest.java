package AlgoritmiaII.entregas.ejercicio5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public final class MatrixFuncsTest {
    private final List<List<Integer>> matrix;
    private final List<List<Integer>> nonSquareMatrix;
    private final List<List<Integer>> emptyMatrix;

    public MatrixFuncsTest() {
        this.matrix = Arrays.asList(
                list(1, 2, 3),
                list(4, 5, 6),
                list(7, 8, 9)
        );
        this.nonSquareMatrix = Arrays.asList(
                list(1, 2, 3),
                list(4, 5, 6)
        );
        this.emptyMatrix = Arrays.asList();
    }

    private List<Integer> list(int... nums){
        List<Integer> lists = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            lists.add(nums[i]);
        }

        return lists;
    }

    @Test
    public void testIsSquareMatrix() {
        assertTrue(MatrixFuncs.isSquareMatrix(matrix));
    }

    @Test
    public void testIsNonSquareMatrix() {
        assertFalse(MatrixFuncs.isSquareMatrix(nonSquareMatrix));
    }

    @Test
    public void testEmptyIsSquareMatrix() {
        assertTrue(MatrixFuncs.isSquareMatrix(emptyMatrix));
    }

    @Test
    public void testGetDiagonalElements() {
        final Integer[] expected = {1, 5, 9};
        assertArrayEquals(expected,
                MatrixFuncs.getDiagonalElements(matrix).orElseThrow().toArray());
    }

    @Test
    public void testGetNonSquareDiagonalElements() {
        assertEquals(Optional.empty(),
                MatrixFuncs.getDiagonalElements(nonSquareMatrix));
    }

    @Test
    public void testGetEmptyDiagonalElements() {
        assertEquals(Optional.empty(),
                MatrixFuncs.getDiagonalElements(emptyMatrix));
    }

    @Test
    public void testGetUpperLeftMatrix() {
        final Integer[][] expected = {{1, 2}, {4, 5}};
        assertEquals(Arrays.deepToString(expected),
                MatrixFuncs.getUpperLeftMatrix(matrix).toString());
    }

    @Test
    public void testGetUpperLeftMatrixNonSquare() {
        final Integer[][] expected = {{1, 2}};
        assertEquals(Arrays.deepToString(expected),
                MatrixFuncs.getUpperLeftMatrix(nonSquareMatrix).toString());
    }

    @Test
    public void testGetUpperLeftMatrixEmpty() {
        final Integer[][] expected = {};
        assertArrayEquals(expected,
                MatrixFuncs.getUpperLeftMatrix(emptyMatrix).toArray());
    }

    @Test
    public void testSumElements() {
        assertEquals(45, MatrixFuncs.sumElements(matrix));
    }

    @Test
    public void testSumElementsNonSquare() {
        assertEquals(21, MatrixFuncs.sumElements(nonSquareMatrix));
    }

    @Test
    public void testSumElementsEmpty() {
        assertEquals(0, MatrixFuncs.sumElements(emptyMatrix));
    }
}
