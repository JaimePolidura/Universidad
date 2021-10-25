package AlgoritmiaI.datastructures.linkedlist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public final class MyLinkedListTest {
    private MyLinkedList<String> list;

    @Before
    public void setUp() {
        this.list = new MyLinkedList<>();
    }

    @Test
    public void testList() {
        assertNull(list.getFirst());
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertArrayEquals(new String[0], list.listData());
    }

    @Test
    public void testClear() {
        list.insert("hola").insert("hola");
        assertFalse(list.isEmpty());
        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test
    public void testInsertOneAtBeginning() {
        list.insert("Hello", 0);
        assertEquals("Hello", list.getFirst());
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"Hello"}, list.listData());
    }

    @Test
    public void testInsertTwoAtBeginning() {
        list.insert("Hello", 0)
                .insert("First", 1);

        assertEquals("Hello", list.getFirst());
        assertEquals("First", list.get(1));
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"Hello", "First"},
                list.listData());
    }

    @Test
    public void testInsertOneAtEnd() {
        list.insert("Hello");
        assertEquals("Hello", list.getFirst());
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"Hello"}, list.listData());
    }

    @Test
    public void testInsertTwoAtEnd() {
        list.insert("Hello").insert("Last");
        assertEquals("Hello", list.getFirst());
        assertEquals("Last", list.get(1));
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"Hello", "Last"},
                list.listData());
    }

    @Test
    public void testInsertAtPosition() {
        list.insert("First", 0)
                .insert("Second", 1)
                .insert("Third", 2);

        assertEquals("First", list.getFirst());
        assertEquals("Second", list.get(1));
        assertEquals("Third", list.get(2));
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"First", "Second", "Third"},
                list.listData());
    }

    @Test
    public void testInsertInMiddle() {
        list.insert("First", 0);
        list.insert("Second", 1);
        list.insert("Third", 2);
        list.insert("Middle", 2);

        assertEquals("First", list.getFirst());
        assertEquals("Second", list.get(1));
        assertEquals("Middle", list.get(2));

        list.get(3);

        assertEquals("Third", list.get(3));
        assertEquals(4, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"First", "Second", "Middle", "Third"}, list.listData());
    }

    @Test
    public void testInsertAtIndexBelowBounds() {
        list.insert("Hello", 0);

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.insert("Invalid", -2));
    }

    @Test
    public void testInsertAtIndexAboveBounds() {
        list.insert("Hello", 0);

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.insert("Invalid", 3));
    }

    @Test
    public void testInsertInEmptyAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.insert("Invalid", 2));
    }

    @Test
    public void testRemoveFirst() {
        list.insert("Hello", 0);
        list.remove(0);

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertArrayEquals(new String[0], list.listData());
    }

    @Test
    public void testRemoveLast() {
        list.insert("Hello", 0).insert("Last", 1);
        list.remove(1);

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"Hello"}, list.listData());
    }

    @Test
    public void testRemoveLastWithOne() {
        list.insert("Hello", 0);
        list.remove(0);

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertArrayEquals(new String[0], list.listData());
    }

    @Test
    public void testRemoveAtPosition() {
        list.insert("First", 0)
                .insert("Second", 1)
                .insert("Third", 2);
        list.remove(1);

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new String[] {"First", "Third"}, list.listData());
    }

    @Test
    public void testRemoveBelowBounds() {
        list.insert("Hello", 0);
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(2));
    }

    @Test
    public void testRemoveBelowBoundsEmpty() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(-2));
    }

    @Test
    public void testRemoveAboveBounds() {
        list.insert("Hello", 0);

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(2));
    }
}
