package AlgoritmiaI.entregas.ejercicio1.ejercicio1;

import org.junit.Test;

import static org.junit.Assert.*;

public final class Ejercicio1Test {
    @Test
    public void testRemoveEs() {
        final String text = "En un lugar de la Mancha, de cuyo nombre no quiero acordarme";
        final String expected = "n un lugar d la Mancha, d cuyo nombr no quiro acordarm";
        final String result = Ejercicio1.removeEs(text);

        assertEquals(expected, result);
    }
}
