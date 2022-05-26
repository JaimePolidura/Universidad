package AlgoritmiaII.entregas.ejercicio6.hash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {
	private static final int CAPACITY = 10;
	private HashTable<String> table;
	
	@BeforeEach
	void setUp() throws Exception {
		table = new HashTable<String>(CAPACITY);
	}

	@Test
	void testHashTable() {
		assertEquals(null, table.get("Mike"));
		assertEquals(0, table.size());
	}

	@Test
	void testSet() {
		final boolean ok = table.set("Mike", "Hello, Mike!");
		assertEquals(true, ok);
		assertEquals("Hello, Mike!", table.get("Mike"));
		assertEquals(1, table.size());
	}
	
	@Test
	void testSetCollision() {
		final boolean okMike = table.set("Mike", "Hello, Mike!");
		final boolean okMaria = table.set("Maria", "Hello, Maria!");
		assertEquals(true, okMike);
		assertEquals(true, okMaria);
		assertEquals("Hello, Mike!", table.get("Mike"));
		assertEquals("Hello, Maria!", table.get("Maria"));
		assertEquals(2, table.size());
	}
	
	@Test
	void testRemove() {
		table.set("Mike", "Hello, Mike!");
		final boolean ok = table.remove("Mike");
		assertEquals(true, ok);
		assertEquals(null, table.get("Mike"));
		assertEquals(0, table.size());
	}
	
	@Test
	void testRemoveKo() {
		final boolean ok = table.remove("Mike");
		assertEquals(false, ok);
		assertEquals(null, table.get("Mike"));
		assertEquals(0, table.size());
	}
}
