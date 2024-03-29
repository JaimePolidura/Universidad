package es.jaime.datastructures.stack;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public final class MyStackTest {
    private MyStack<Integer> stack;

    @Before
    public void setUp() {
        this.stack = new MyStack<>();
    }

    @Test
    public void testStack() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
        assertThrows(EmptyStackException.class, () -> stack.top());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertArrayEquals(new Integer[0], stack.listData());
    }

    @Test
    public void testPushOne() {
        stack.push(10);

        assertEquals(10, stack.top());
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertArrayEquals(new Integer[]{10}, stack.listData());
    }

    @Test
    public void testPushThree() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.top());
        assertFalse(stack.isEmpty());
        assertEquals(3, stack.size());
        assertArrayEquals(new Integer[]{30, 20, 10}, stack.listData());
    }

    @Test
    public void testPopOneFromThree() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        final Integer elem = stack.pop();

        assertEquals(30, elem);
        assertEquals(20, stack.top());
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
        assertArrayEquals(new Integer[]{20, 10}, stack.listData());
    }

    @Test
    public void testPopThreeFromThree() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        final Integer elem1 = stack.pop();
        final Integer elem2 = stack.pop();
        final Integer elem3 = stack.pop();

        assertEquals(30, elem1);
        assertEquals(20, elem2);
        assertEquals(10, elem3);
        assertThrows(EmptyStackException.class, () -> stack.top());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertArrayEquals(new Integer[0], stack.listData());
    }
}
