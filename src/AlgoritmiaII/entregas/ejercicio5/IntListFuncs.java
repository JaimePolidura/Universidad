package AlgoritmiaII.entregas.ejercicio5;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class IntListFuncs {
    public static List<Integer> countUpDown(int from, int to) {
        return IntStream.concat(IntStream.range(from, to), IntStream.iterate(to, i -> i >= from, i -> i - 1))
                .boxed()
                .toList();
    }

    public static List<List<Integer>> splitEach(List<Integer> input, int itemsPerList) {
        int numberOfPartitions = input.size() / itemsPerList + (input.size() % itemsPerList == 0 ? 0 : 1);

        return Stream.iterate(0, i -> i < numberOfPartitions, i -> i + 1)
                .map(i -> sublist(input, i * itemsPerList, itemsPerList + itemsPerList * i))
                .collect(Collectors.toList());
    }

    private static <T> List<T> sublist(List<T> input, int from, int to) {
        return input.size() < to ? input.subList(from, to - 1) : input.subList(from, to);
    }

    public static int parityBitSum(List<Integer> input) {
        return input.stream()
                .map(String::valueOf)
                .mapToInt(s -> countCharInString(s,'1'))
                .sum();
    }

    private static int countCharInString(String string, char character){
        return (int) string.chars()
                .mapToObj(i-> (char) i)
                .filter(chars -> chars == character)
                .count();
    }
}
