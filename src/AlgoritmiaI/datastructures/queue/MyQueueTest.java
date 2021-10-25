package AlgoritmiaI.datastructures.queue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public final class MyQueueTest {
    private MyQueue<String> queueToTest;

    @Before
    public void setUp () {
        this.queueToTest = new MyQueue<>();
    }

    @Test
    public void enqueue() {
        fillQueue();
        String[] expectedValues = new String[]{"Paco", "Paula", "Juan"};

        assertEquals(3, queueToTest.size());
        assertArrayEquals(expectedValues, queueToTest.listData());
    }

    @Test
    public void dequeue () {
        fillQueue();

        String first = queueToTest.dequeue();
        assertEquals("Paco", first);
        assertEquals(2, queueToTest.size());
        System.out.println(".....");
        String second = queueToTest.dequeue();
        assertEquals("Paula", second);
        assertEquals(1, queueToTest.size());

        String third = queueToTest.dequeue();
        assertEquals("Juan", third);
        assertEquals(0, queueToTest.size());
    }

    private void fillQueue () {
        this.queueToTest.enqueue("Juan");
        this.queueToTest.enqueue("Paula");
        this.queueToTest.enqueue("Paco");
    }
}
