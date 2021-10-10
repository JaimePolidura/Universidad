package AlgoritmiaI.practica1.ejercicio3;

import org.junit.Test;

import static org.junit.Assert.*;

public final class MatrixTest {
    private static final int[][] vals = { { 1, 2 }, { 3, 4 }, };
    private static final int[][] vals2 = { { 10, 20 }, { 30, 40 } };
    private static final int[][] vals3 = { { 100, 200 } };

    @Test
    public void testMatrix() {
        final Matrix mat = new Matrix(vals);

        assertEquals(1, mat.getRC(0, 0));
        assertEquals(2, mat.getRC(0, 1));
        assertEquals(3, mat.getRC(1, 0));
        assertEquals(4, mat.getRC(1, 1));
    }
    @Test
    public void testMatrixNull() {
        assertThrows(NullPointerException.class, () -> new Matrix(null));
    }

    @Test
    public void testSetRC() {
        final Matrix mat = new Matrix(vals);
        mat.setRC(0, 0, 10);
        mat.setRC(1, 0, 30);

        assertEquals(10, mat.getRC(0, 0));
        assertEquals(2, mat.getRC(0, 1));
        assertEquals(30, mat.getRC(1, 0));
        assertEquals(4, mat.getRC(1, 1));
    }
    @Test
    public void testSetRCOutOfBounds() {
        final Matrix mat = new Matrix(vals);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> mat.setRC(2, 0,
                100));
    }

    @Test
    public void testAdd() throws MatrixException {
        final Matrix mat1 = new Matrix(vals);
        final Matrix mat2 = new Matrix(vals2);
        final Matrix resultado = mat1.add(mat2);

        assertEquals(11, resultado.getRC(0, 0));
        assertEquals(22, resultado.getRC(0, 1));
        assertEquals(33, resultado.getRC(1, 0));
        assertEquals(44, resultado.getRC(1, 1));
    }

    @Test
    public void testAddOrderMismatch() {
        final Matrix mat1 = new Matrix(vals);
        final Matrix mat2 = new Matrix(vals3);
        final MatrixException ex = assertThrows(MatrixException.class, () ->
                mat1.add(mat2));
        assertEquals("Cannot add matrices of different order", ex.getMessage());
    }

    @Test
    public void testMultiply() throws MatrixException {
        final Matrix mat1 = new Matrix(vals);
        final Matrix mat2 = new Matrix(vals2);
        final Matrix resultado = mat1.multiply(mat2);

        assertEquals(70, resultado.getRC(0, 0));
        assertEquals(100, resultado.getRC(0, 1));
        assertEquals(150, resultado.getRC(1, 0));
        assertEquals(220, resultado.getRC(1, 1));
    }

    @Test
    public void testMultiplyOrderMismatch() {
        final Matrix mat1 = new Matrix(vals);
        final Matrix mat2 = new Matrix(vals3);
        final MatrixException ex = assertThrows(MatrixException.class, () ->
                mat1.multiply(mat2));

        assertEquals(
                "Cannot multiply matrices if number of columns in left matrix is not equal to number of rows in right matrix",
                ex.getMessage());
    }

    @Test
    public void testEquals() {
        final Matrix mat1 = new Matrix(vals);
        final Matrix mat2 = new Matrix(vals);

        assertTrue(mat1.equals(mat2));
    }

    @Test
    public void testNotEquals() {
        final Matrix mat1 = new Matrix(vals);
        final Matrix mat2 = new Matrix(vals2);

        assertFalse(mat1.equals(mat2));
    }
}
