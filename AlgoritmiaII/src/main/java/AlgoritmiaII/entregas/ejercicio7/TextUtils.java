package AlgoritmiaII.entregas.ejercicio7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextUtils {
    public static String trimAndClean(String text) {
        return getCharactersStream(text)
                .filter(Character::isLetterOrDigit)
                .map(String::valueOf)
                .reduce("", (acc, c) -> acc + c )
                .toLowerCase();
    }

    private static Stream<Character> getCharactersStream(String text) {
        return Stream.iterate(0, i -> i< text.length(), i -> i +1)
                .map(text::charAt);

    }
}
