package AlgoritmiaI.practica0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public final class PrimeNumbersTest {
    private PrimeNumberCollection primeNumberCollectionToTest;

    @Before
    public void setUp() {
        int[] arrayToTest = new int[]{4, 13, 12, 15, 17};
        this.primeNumberCollectionToTest = PrimeNumbers.getPrimeCollection(arrayToTest);
    }

    @Test
    public void primeNumbersTest () {
        int[] expectedPrimes = new int[]{0, 13, 0, 0, 17};

        assertArrayEquals(expectedPrimes, primeNumberCollectionToTest.primeNumbers);
    }

    @Test
    public void maxValueTest() {
        assertEquals(17, primeNumberCollectionToTest.maxValue);
    }

    @Test
    public void minValueTest() {
        assertEquals(4, primeNumberCollectionToTest.minValue);
    }
}
