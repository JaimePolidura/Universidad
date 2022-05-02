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

    public static void main(String[] args) {
        splitEach(List.of(1,2,3,4,5),2);
    }

    public static List<List<Integer>> splitEach(List<Integer> input, int total) {
        int itemsPerList = input.size() / total;

        return Stream.iterate(0, i -> i < itemsPerList, i -> i + 1)
                .map(i -> input.subList(i * itemsPerList, i + itemsPerList * i))
                .collect(Collectors.toList());
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
