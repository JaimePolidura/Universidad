package es.jaime.entregas.ejercicio6;

import org.graphstream.graph.Graph;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ProgramTest {
	@Test
	public void testCreateGraph() {
		final Graph graph = Program.createGraph();

		assertEquals(7, graph.getNodeCount());
		assertEquals(12, graph.getEdgeCount());
	}

	@Test
	public void testCreateMatrix() {
		final String[] result = Program.createMatrix(Program.createGraph());
		final String[] expected = {
			"A (0, -)",
			"B (3, A)",
			"C (5, A)",
			"D (6, B)",
			"E (5, B)",
			"F (6, E)",
			"Z (7, E)"
		};

		assertArrayEquals(expected, result);
	}
}
