package es.laberinto.bloques;

import es.laberinto.entides.Caballo;
import es.laberinto.entides.Entidad;

import java.util.List;

public final class CespedAlto extends Bloque {
    @Override
    public double velocidad() {
        return 2;
    }

    @Override
    public boolean todasLasEntidadesPuedenTransitar() {
        return false;
    }

    @Override
    public List<Class<? extends Entidad>> soloTransitableCon() {
        return List.of(Caballo.class);
    }

    public static CespedAlto crear() {
        return new CespedAlto();
    }
}
