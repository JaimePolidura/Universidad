package es.laberinto.modelo.bloques;

import es.laberinto.modelo.entides.Alfombra;
import es.laberinto.modelo.entides.Entidad;

public final class Montana extends Bloque {
    @Override
    public double velocidad() {
        return 1;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return entidad.getClass() == Alfombra.class;
    }

    public static Montana crear() {
        return new Montana();
    }
}
