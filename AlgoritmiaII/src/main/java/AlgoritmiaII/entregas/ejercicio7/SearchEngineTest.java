package AlgoritmiaII.entregas.ejercicio7;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SearchEngineTest {
	private static final String JAVA_URL = "https://en.wikipedia.org/w/index.php?title=Java_(programming_language)&oldid=1022204719";
	private static final String SCALA_URL = "https://en.wikipedia.org/w/index.php?title=Scala_(programming_language)&oldid=1022192959";
	
	private static final SearchEngine engine = new SearchEngine();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		engine.cacheUrl(JAVA_URL);
		engine.cacheUrl(SCALA_URL);
	}

	@Test
	void testCacheUrl() {
		assertTrue(new SearchEngine().cacheUrl(JAVA_URL));
	}
	
	@Test
	void testCacheUrlFalse() {
		final SearchEngine engine = new SearchEngine();
		engine.cacheUrl(JAVA_URL);
		assertFalse(engine.cacheUrl(JAVA_URL));
	}

	@Test
	void testSearchJava() {
		final String[] expected = {JAVA_URL, SCALA_URL};
		assertArrayEquals(expected, engine.search(Arrays.asList("java")).toArray());
	}
	
	@Test
	void testSearchScala() {
		final String[] expected = {SCALA_URL, JAVA_URL};
		assertArrayEquals(expected, engine.search(Arrays.asList("scala")).toArray());
	}
	
	@Test
	void testSearchFunctionalProgramming() {
		final String[] expected = {SCALA_URL, JAVA_URL};
		assertArrayEquals(expected, engine.search(Arrays.asList("functional", "programming")).toArray());
	}
	
	@Test
	void getTermIdfFunctional() {
		assertEquals(1.693, engine.getTermIdf("functional"), 0.001);
	}
	
	@Test
	void getTermIdfProgramming() {
		assertEquals(1, engine.getTermIdf("programming"));
	}
	
	@Test
	void getTermIdfJava() {
		assertEquals(1, engine.getTermIdf("java"));
	}
	
	@Test
	void getTermIdfScala() {
		assertEquals(1, engine.getTermIdf("scala"));
	}
}
