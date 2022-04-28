package AlgoritmiaII.entregas.ejercicio5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class MatrixFuncs {
    public static boolean isSquareMatrix(List<List<Integer>> matrix) {
        return matrix.stream()
                .map(List::size)
                .allMatch(n -> n == matrix.size());
    }

    public static Optional<List<Integer>> getDiagonalElements(List<List<Integer>> matrix) {
        List<Integer> diagonal = new ArrayList<>();

        return isSquareMatrix(matrix) ?
                Optional.empty() :
                Optional.ofNullable(IntStream.of(0, matrix.size())
                        .mapToObj(i -> matrix.get(i).get(i))
                        .collect(Collectors.toList()));
    }

    public static List<List<Integer>> getUpperLeftMatrix(List<List<Integer>> matrix) {
        return matrix.size() == 0 ?
                Collections.EMPTY_LIST :
                matrix.subList(0, matrix.size() - 1).stream()
                        .map(list -> list.subList(0, list.size() - 1))
                        .collect(Collectors.toList());
    }

    public static int sumElements(List<List<Integer>> matrix) {
        return matrix.stream()
                .map(list -> list.stream().reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }
}
