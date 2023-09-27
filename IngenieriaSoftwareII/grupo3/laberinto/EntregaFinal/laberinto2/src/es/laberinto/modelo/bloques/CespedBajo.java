package es.laberinto.modelo.bloques;

import es.laberinto.modelo.entides.Entidad;
import es.laberinto.modelo.entides.Personaje;

public final class CespedBajo extends Bloque {
    @Override
    public double velocidad() {
        return 1;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return entidad.getClass() == Personaje.class;
    }

    public static CespedBajo crear() {
        return new CespedBajo();
    }
}
