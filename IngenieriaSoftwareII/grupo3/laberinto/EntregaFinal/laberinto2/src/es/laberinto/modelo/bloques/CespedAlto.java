package es.laberinto.modelo.bloques;

import es.laberinto.modelo.entides.Caballo;
import es.laberinto.modelo.entides.Entidad;

public final class CespedAlto extends Bloque {
    @Override
    public double velocidad() {
        return 2;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return entidad.getClass() == Caballo.class;
    }

    public static CespedAlto crear() {
        return new CespedAlto();
    }
}
