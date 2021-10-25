package AlgoritmiaI.entregas.ejercicio3;

import AlgoritmiaI.datastructures.stack.MyStack;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class ClosureAnalyzerTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "1+2",
            "(1+2)",
            "1+(2*3)",
            "(1+2)+(3*4)",
            "1+(2+array[3*4])"
    })
    public void emptyTextHasNoErrors(String expression) {
        assertNull(ClosureAnalyzer.getFirstError(expression));
    }

    @ParameterizedTest
    @CsvSource({
            "1+(2*3, 2",
            "(1+2, 0",
            "1+(2*3)+(4, 8",
            "1+array[1, 7",
            "array0]+1, 6",
            "(1+array[1), 10",
            "(1+array[1], 0"
    })
    public void errorForNotClosedParentheses(String expression, int expectedIndexError) {
        SyntaxError error = ClosureAnalyzer.getFirstError(expression);
        assertEquals(expectedIndexError, error.getIndex());
    }

    @ParameterizedTest
    @MethodSource("extraClosingParenthesesCases")
    public void errorForExtraClosingParentheses(String expression, int expectedIndexError) {
        SyntaxError error = ClosureAnalyzer.getFirstError(expression);
        assertEquals(expectedIndexError, error.getIndex());
    }

    @Test
    public void errorContainsContextStack() {
        String expression = "1 + (N * (1+array[0 / 2) * ((N-1)*array[1]/ 3)";
        SyntaxError error = ClosureAnalyzer.getFirstError((expression));
        MyStack<String> contextStack = error.getContextStack();
        assertEquals("[0 / 2)", contextStack.pop());
        assertEquals("(1+array[0 / 2)", contextStack.pop());
        assertEquals("(N * (1+array[0 / 2)", contextStack.pop());
        assertEquals("1 + (N * (1+array[0 / 2)", contextStack.pop());
        assertTrue(contextStack.isEmpty());
    }

    private static Arguments[] extraClosingParenthesesCases() {
        return new Arguments[]{
                arguments("1+2)", 3),
                arguments("1+(2*3))", 7),
                arguments("1+(2*3))+4", 7),
                arguments("1+array[0]]", 10),
                arguments("1+array[0]]+array[1]", 10)
        };
    }
}
