package es.laberinto.modelo.bloques;

import es.laberinto.modelo.entides.Entidad;

public final class Pared extends Bloque {
    @Override
    public double velocidad() {
        return 0;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return false;
    }

    public static Pared crear() {
        return new Pared();
    }
}
