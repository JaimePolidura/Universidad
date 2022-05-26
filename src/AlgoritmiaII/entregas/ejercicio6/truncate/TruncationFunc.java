package AlgoritmiaII.entregas.ejercicio6.truncate;

import java.util.function.Function;

public final class TruncationFunc implements Function<String, Integer> {
    public Integer apply(String text) {
        String hash = String.valueOf(text.hashCode()).replace("-","");
        int elementsPerSize = hash.length() / 3;

        return Integer.parseInt(String.format("%s%s%s",
                hash.substring(0, elementsPerSize).charAt(0),
                hash.substring(elementsPerSize, elementsPerSize * 2).charAt(0),
                hash.substring(elementsPerSize * 2).charAt(0)));
    }
}
