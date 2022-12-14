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
        List<Integer> masks = Stream.iterate(0, n -> n < 32, n -> n + 1)
                .map(n -> 0b0001 << n)
                .toList();

        masks.stream().forEach(n-> System.out.println(Integer.toBinaryString(n)));

        return input.stream()
                .filter(num -> masks.stream().anyMatch(mask -> (mask & num) > 0))
                .toList()
                .size();
    }

    private static int countCharInString(String string, char character){
        return (int) string.chars()
                .mapToObj(i-> (char) i)
                .filter(chars -> chars == character)
                .count();
    }
}
