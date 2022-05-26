package AlgoritmiaII.entregas.ejercicio6.truncate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TruncationFuncTest {
    @ParameterizedTest
    @CsvSource({
            "104, 'Hello, world!'",
            "119, 'How are you?'",
            "277, 'Goodbye!'"
    })
    void testTruncateHash(int expected, String text) {
        assertEquals(expected, new TruncationFunc().apply(text));
    }
}
