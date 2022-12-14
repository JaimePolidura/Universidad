package es.jaime.entregas.ejercicio1.ejercicio3;

import java.util.Arrays;
import java.util.Objects;

public final class Matrix {
    private int[][] array;

    public Matrix(int[][] array) {
        Objects.requireNonNull(array);

        copyMatrix(array);
    }

    private void copyMatrix(int[][] array) {
        this.array = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++){
                this.array[i][j] = array[i][j];
            }
        }
    }

    public void setRC(int row, int column, int newValue) throws ArrayIndexOutOfBoundsException {
        checkIfIndexExitsOrThrow(row, column);

        array[row][column] = newValue;
    }

    public int getRC(int row, int column) throws ArrayIndexOutOfBoundsException {
        checkIfIndexExitsOrThrow(row, column);

        return array[row][column];
    }

    public Matrix add(Matrix matrixToSum) throws MatrixException {
        Objects.requireNonNull(matrixToSum);
        checkIfSameSizeMatrix(matrixToSum);

        Matrix newMatrix = new Matrix(new int[array.length][array[0].length]);

        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                newMatrix.array[i][j] = array[i][j] + matrixToSum.array[i][j];
            }
        }

        return newMatrix;
    }

    private void checkIfSameSizeMatrix (Matrix otherMatrix) throws MatrixException {
        if(array.length != otherMatrix.array.length || array[0].length != otherMatrix.array[0].length){
            throw new MatrixException("Cannot add matrices of different order");
        }
    }

    public Matrix multiply(Matrix toMultiply) throws MatrixException {
        Objects.requireNonNull(toMultiply);
        checkIfCanBeMultiplied(toMultiply);

        Matrix newMatrix = new Matrix(new int[array.length][toMultiply.array[0].length]);

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < toMultiply.array[0].length; j++){
                for(int k = 0; k < toMultiply.array.length; k++){
                    newMatrix.array[i][j] += array[i][k] * toMultiply.array[k][j];
                }
            }
        }

        return newMatrix;
    }

    private void checkIfCanBeMultiplied(Matrix toMultiply) throws MatrixException {
        boolean cannotBeMultiplied = this.array.length != toMultiply.array[0].length
                || this.array[0].length != toMultiply.array.length;

        if(cannotBeMultiplied){
            throw new MatrixException( "Cannot multiply matrices if number of columns in left matrix is not equal to number of rows in right matrix");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.deepEquals(array, matrix.array);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(array);
    }

    private void checkIfIndexExitsOrThrow(int row, int column) {
        if(row < 0 || column < 0 || row >= array.length || column >= array[0].length){
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
