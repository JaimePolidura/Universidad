package es.laberinto.bloques;

import es.laberinto.entides.Barca;
import es.laberinto.entides.Entidad;

import java.util.List;

public final class Agua extends Bloque {
    @Override
    public double velocidad() {
        return 1;
    }

    @Override
    public boolean todasLasEntidadesPuedenTransitar() {
        return false;
    }

    @Override
    public List<Class<? extends Entidad>> soloTransitableCon() {
        return List.of(Barca.class);
    }

    public static Agua crear() {
        return new Agua();
    }
}
