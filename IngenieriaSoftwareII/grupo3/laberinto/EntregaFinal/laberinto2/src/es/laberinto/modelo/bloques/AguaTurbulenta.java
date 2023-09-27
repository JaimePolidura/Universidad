package es.laberinto.modelo.bloques;

import es.laberinto.modelo.entides.Barca;
import es.laberinto.modelo.entides.Entidad;

public final class AguaTurbulenta extends Bloque {
    @Override
    public double velocidad() {
        return 2;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return entidad.getClass() == Barca.class;
    }

    public static AguaTurbulenta crear() {
        return new AguaTurbulenta();
    }
}
