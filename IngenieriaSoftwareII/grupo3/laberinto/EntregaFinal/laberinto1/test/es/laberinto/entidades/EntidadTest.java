package es.laberinto.entidades;

import es.laberinto.Mundo;
import es.laberinto.bloques.Bloque;
import es.laberinto.entides.Barca;
import es.laberinto.entides.Caballo;
import es.laberinto.entides.Entidad;
import es.laberinto.entides.Personaje;
import es.laberinto.utils.Direccion;
import es.laberinto.utils.Posicion;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public final class EntidadTest {
    @Test
    public void mover_seMuevaMientrasVaMontado() {
        Posicion posicionInicial = Posicion.crear(0, 0);
        Posicion posicionArriba = Posicion.crear(0, 1);

        Mundo mundo = mock(Mundo.class);
        Entidad entidad = new Personaje(mundo);
        Entidad entidadALaQueVaMontada = Barca.crear(mundo, posicionInicial);
        Bloque bloqueArriba = mock(Bloque.class);
        entidad.setPosicionActual(posicionInicial);
        entidad.setEntidadSobreLaQueEstoyMontado(entidadALaQueVaMontada);
        entidadALaQueVaMontada.setEntidadMontadaSobreMi(entidad);

        when(mundo.getBloque(posicionArriba)).thenReturn(bloqueArriba);
        when(bloqueArriba.puedeTransitar(entidad)).thenReturn(true);
        when(bloqueArriba.velocidad()).thenReturn(1.0d);

        boolean seHaMovido2 = entidad.mover(Direccion.ARRIBA.getVector());
        assertTrue(seHaMovido2);
        assertEquals(entidad.getPosicionActual(), posicionArriba);
        assertEquals(entidadALaQueVaMontada.getPosicionActual(), posicionArriba);
    }

    @Test
    public void mover_aumentaBufferMovimientoYCambiaDireccion() {
        Posicion posicionInicial = Posicion.crear(0, 0);
        Posicion posicionArriba = Posicion.crear(0, 1);
        Posicion posicionAbajo = Posicion.crear(0, -1);

        Mundo mundo = mock(Mundo.class);
        Entidad entidad = new Personaje(mundo);
        Bloque bloqueArriba = mock(Bloque.class);
        Bloque bloqueAbajo = mock(Bloque.class);
        entidad.setPosicionActual(posicionInicial);

        when(mundo.getBloque(posicionArriba)).thenReturn(bloqueArriba);
        when(mundo.getBloque(posicionAbajo)).thenReturn(bloqueAbajo);
        when(bloqueArriba.puedeTransitar(entidad)).thenReturn(true);
        when(bloqueArriba.velocidad()).thenReturn(0.5d);
        when(bloqueAbajo.puedeTransitar(entidad)).thenReturn(true);
        when(bloqueAbajo.velocidad()).thenReturn(1.0d);

        boolean seHaMovido1 = entidad.mover(Direccion.ARRIBA.getVector());
        assertFalse(seHaMovido1);
        assertEquals(entidad.getPosicionActual(), posicionInicial);
        assertEquals(entidad.getBufferMovimeintoAnteriorTurno(), 0.5d);
        assertEquals(entidad.getPosicionDondeQuiereMoverseAnteriorTurno(), posicionArriba);

        boolean seHaMovido2 = entidad.mover(Direccion.ABAJO.getVector());
        assertTrue(seHaMovido2);
        assertEquals(entidad.getPosicionActual(), posicionAbajo);
        assertEquals(entidad.getBufferMovimeintoAnteriorTurno(), 0.0d);
        assertNull(entidad.getPosicionDondeQuiereMoverseAnteriorTurno());
    }

    @Test
    public void mover_aumentaBufferMovimiento2veces() {
        Posicion posicionInicial = Posicion.crear(0, 0);
        Posicion posicionArriba = Posicion.crear(0, 1);

        Mundo mundo = mock(Mundo.class);
        Entidad entidad = new Personaje(mundo);
        Bloque bloqueArriba = mock(Bloque.class);
        entidad.setPosicionActual(posicionInicial);

        when(mundo.getBloque(posicionArriba)).thenReturn(bloqueArriba);
        when(bloqueArriba.puedeTransitar(entidad)).thenReturn(true);
        when(bloqueArriba.velocidad()).thenReturn(0.5d);

        boolean seHaMovido1 = entidad.mover(Direccion.ARRIBA.getVector());
        assertFalse(seHaMovido1);
        assertEquals(entidad.getPosicionActual(), posicionInicial);
        assertEquals(entidad.getBufferMovimeintoAnteriorTurno(), 0.5d);
        assertEquals(entidad.getPosicionDondeQuiereMoverseAnteriorTurno(), posicionArriba);

        boolean seHaMovido2 = entidad.mover(Direccion.ARRIBA.getVector());
        assertTrue(seHaMovido2);
        assertEquals(entidad.getPosicionActual(), posicionArriba);
        assertEquals(entidad.getBufferMovimeintoAnteriorTurno(), 0.0d);
        assertNull(entidad.getPosicionDondeQuiereMoverseAnteriorTurno());
    }

    @Test
    public void mover_velocidadBloque2_SeMontaEnEntidad() {
        Posicion posicionSiguienteBloque = Posicion.crear(0, 1);
        Posicion posicionMoverse = Posicion.crear(0, 2);

        Mundo mundo = mock(Mundo.class);
        Entidad entidad = new Personaje(mundo);
        Entidad entidadPosicionMoverse = mock(Entidad.class);
        Bloque siguienteBloque = mock(Bloque.class);
        entidad.setPosicionActual(Posicion.crear(0, 0));

        when(mundo.getBloque(posicionSiguienteBloque)).thenReturn(siguienteBloque);
        when(mundo.getEntidad(posicionSiguienteBloque)).thenReturn(null);
        when(siguienteBloque.puedeTransitar(entidad)).thenReturn(true);
        when(siguienteBloque.velocidad()).thenReturn(2.0d);

        when(mundo.getEntidad(posicionMoverse)).thenReturn(entidadPosicionMoverse);
        when(mundo.getBloque(posicionMoverse)).thenReturn(siguienteBloque);

        when(entidadPosicionMoverse.otraEntidadPuedeMontarse()).thenReturn(true);
        when(entidadPosicionMoverse.otraEntidadEstaMontada()).thenReturn(false);

        boolean seHaMovido1 = entidad.mover(Direccion.ARRIBA.getVector());
        assertTrue(seHaMovido1);
        assertEquals(entidad.getPosicionActual(), posicionMoverse);
        assertEquals(entidad.getBufferMovimeintoAnteriorTurno(), 0.0d);

        assertEquals(entidad.getEntidadSobreLaQueEstoyMontado(), entidadPosicionMoverse);
    }

    @Test
    public void noMover_velocidadBloque2EntidadColision() {
        Posicion posicionSiguienteBloque = Posicion.crear(0, 1);
        Posicion posicionMoverse = Posicion.crear(0, 2);

        Mundo mundo = mock(Mundo.class);
        Entidad entidad = new Personaje(mundo);
        Entidad entidadPosicionMoverse = mock(Entidad.class);
        Bloque siguienteBloque = mock(Bloque.class);
        entidad.setPosicionActual(Posicion.crear(0, 0));

        when(mundo.getBloque(posicionSiguienteBloque)).thenReturn(siguienteBloque);
        when(mundo.getEntidad(posicionSiguienteBloque)).thenReturn(null);
        when(siguienteBloque.puedeTransitar(entidad)).thenReturn(true);
        when(siguienteBloque.velocidad()).thenReturn(2.0d);

        when(mundo.getEntidad(posicionMoverse)).thenReturn(entidadPosicionMoverse);
        when(mundo.getBloque(posicionMoverse)).thenReturn(siguienteBloque);

        when(entidadPosicionMoverse.otraEntidadPuedeMontarse()).thenReturn(false);
        boolean seHaMovido1 = entidad.mover(Direccion.ARRIBA.getVector());
        assertFalse(seHaMovido1);

        when(entidadPosicionMoverse.otraEntidadPuedeMontarse()).thenReturn(true);
        when(entidadPosicionMoverse.otraEntidadEstaMontada()).thenReturn(true);
        boolean seHaMovido2 = entidad.mover(Direccion.ARRIBA.getVector());
        assertFalse(seHaMovido2);
    }

    @Test
    public void noMover_velocidadBloque2BloqueNoTransitable() {
        Posicion posicionSiguienteBloque = Posicion.crear(0, 1);
        Posicion posicionMoverse = Posicion.crear(0, 2);

        Mundo mundo = mock(Mundo.class);
        Entidad entidad = new Personaje(mundo);
        Bloque siguienteBloque = mock(Bloque.class);
        Bloque bloquePosicoinMoverse = mock(Bloque.class);
        entidad.setPosicionActual(Posicion.crear(0, 0));

        when(mundo.getBloque(posicionSiguienteBloque)).thenReturn(siguienteBloque);
        when(mundo.getEntidad(posicionSiguienteBloque)).thenReturn(null);
        when(siguienteBloque.puedeTransitar(entidad)).thenReturn(true);
        when(siguienteBloque.velocidad()).thenReturn(2.0d);

        when(mundo.getEntidad(posicionMoverse)).thenReturn(null);
        when(mundo.getBloque(posicionMoverse)).thenReturn(bloquePosicoinMoverse);
        when(siguienteBloque.puedeTransitar(entidad)).thenReturn(false);

        boolean seHaMovido = entidad.mover(Direccion.ARRIBA.getVector());
        assertFalse(seHaMovido);
        assertEquals(entidad.getPosicionActual(), Posicion.crear(0, 0));
    }

    @Test
    public void mover_velocidadBloque2() {
        Posicion posicionSiguienteBloque = Posicion.crear(0, 1);
        Posicion posicionMoverse = Posicion.crear(0, 2);

        Mundo mundo = mock(Mundo.class);
        Entidad entidad = new Personaje(mundo);
        Bloque siguienteBloque = mock(Bloque.class);
        entidad.setPosicionActual(Posicion.crear(0, 0));

        when(mundo.getBloque(posicionSiguienteBloque)).thenReturn(siguienteBloque);
        when(mundo.getEntidad(posicionSiguienteBloque)).thenReturn(null);
        when(siguienteBloque.puedeTransitar(entidad)).thenReturn(true);
        when(siguienteBloque.velocidad()).thenReturn(2.0d);

        when(mundo.getEntidad(posicionMoverse)).thenReturn(null);
        when(mundo.getBloque(posicionMoverse)).thenReturn(siguienteBloque);
        when(siguienteBloque.puedeTransitar(entidad)).thenReturn(true);

        boolean seHaMovido = entidad.mover(Direccion.ARRIBA.getVector());
        assertTrue(seHaMovido);
        assertEquals(entidad.getPosicionActual(), posicionMoverse);
        assertEquals(entidad.getBufferMovimeintoAnteriorTurno(), 0.0d);
    }

    @Test
    public void noMover_hayColision() {
        Posicion posicionAMoverse = Posicion.crear(0, 1);

        Mundo mundo = mock(Mundo.class);
        Entidad entidad = new Personaje(mundo);
        Entidad entidadAColisionar = mock(Entidad.class);
        Bloque bloqueAMoverse = mock(Bloque.class);
        entidad.setPosicionActual(Posicion.crear(0, 0));

        when(mundo.getBloque(posicionAMoverse)).thenReturn(bloqueAMoverse);
        when(bloqueAMoverse.puedeTransitar(entidad)).thenReturn(true);
        when(mundo.getEntidad(posicionAMoverse)).thenReturn(entidadAColisionar);

        when(entidadAColisionar.otraEntidadPuedeMontarse()).thenReturn(false);
        boolean seHaMovido1 = entidad.mover(Direccion.ARRIBA.getVector());
        assertFalse(seHaMovido1);

        when(entidadAColisionar.otraEntidadPuedeMontarse()).thenReturn(true);
        when(entidadAColisionar.otraEntidadEstaMontada()).thenReturn(true);
        boolean seHaMovido2 = entidad.mover(Direccion.ARRIBA.getVector());
        assertFalse(seHaMovido2);
    }

    @Test
    public void noMover_siguienteBloqueNoTransitable() {
        Mundo mundo = mock(Mundo.class);
        Entidad entidad = new Personaje(mundo);
        Bloque bloqueNoTransitable = mock(Bloque.class);
        entidad.setPosicionActual(Posicion.crear(0, 0));

        when(bloqueNoTransitable.puedeTransitar(entidad)).thenReturn(false);
        when(mundo.getBloque(Posicion.crear(0, 1))).thenReturn(bloqueNoTransitable);

        boolean seHaMovido = entidad.mover(Direccion.ARRIBA.getVector());

        assertFalse(seHaMovido);
    }

    @Test
    public void noMover_otraEntidadEstaMondtada() {
        Mundo mundo = mock(Mundo.class);
        Personaje entidad = new Personaje(mundo);
        entidad.setPosicionActual(Posicion.crear(0, 0));
        entidad.setEntidadMontadaSobreMi(new Barca(null));

        when(mundo.posicionFueraDeLosLimites(any())).thenReturn(false);

        boolean seHaMovido = entidad.mover(Direccion.aleatorio().getVector());

        assertFalse(seHaMovido);
    }
}
