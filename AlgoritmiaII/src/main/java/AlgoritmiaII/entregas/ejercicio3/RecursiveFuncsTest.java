package AlgoritmiaII.entregas.ejercicio3;

import org.junit.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public final class RecursiveFuncsTest {
    @Test
    public void testInvertDigits() {
        assertEquals(1001, RecursiveFuncs.invertDigits(1001));
        assertEquals(4201, RecursiveFuncs.invertDigits(1024));
        assertEquals(-321, RecursiveFuncs.invertDigits(-123));
    }

    @Test
    public void testSumArrayElements() {
        assertEquals(0, RecursiveFuncs.sumArrayElements(new int[] {}));
        assertEquals(131, RecursiveFuncs.sumArrayElements(
                new int[] {1, 10, 20, 100}));
    }

    @Test
    public void testSumMatrixElements() {
        assertEquals(0, RecursiveFuncs.sumMatrixElements(
                new int[][] {}));
        assertEquals(100, RecursiveFuncs.sumMatrixElements(
                new int[][] {{1, 99}}));
        assertEquals(220, RecursiveFuncs.sumMatrixElements(
                new int[][] {{1, 99}, {20, 100}}));
    }

    @Test
    public void testRequest() {
        final TestRequestable requestable =
                new TestRequestable("https://www.google.com", "OK", 0);
        final String response = RecursiveFuncs.request(requestable, 5);
        assertEquals("OK", response);
        assertEquals(1, requestable.getNumAttempts());
    }

    @Test
    public void testRequest3() {
        final TestRequestable requestable =
                new TestRequestable("https://www.google.com", "OK", 3);
        final String response = RecursiveFuncs.request(requestable, 5);
        assertEquals("OK", response);
        assertEquals(4, requestable.getNumAttempts());
    }

    @Test
    public void testRequest6() {
        final TestRequestable requestable =
                new TestRequestable("https://www.google.com", "OK", 6);
        final String response = RecursiveFuncs.request(requestable, 5);
        assertNull(response);
        assertEquals(5, requestable.getNumAttempts());
    }
}

class TestRequestable implements Requestable {
    private final String url;
    private final String content;
    private final int numFails;
    private int numAttempts;

    public TestRequestable(String url, String content, int numFails) {
        this.url = url;
        this.content = content;
        this.numFails = numFails;
        this.numAttempts = 0;
    }

    public String request() throws IOException {
        numAttempts++;
        if (content != null && numAttempts > numFails) {
            return content;
        } else {
            throw new IOException(
                    "Can't establish connection to '" + this.url + "'");
        }
    }

    public int getNumAttempts() {
        return numAttempts;
    }
}
