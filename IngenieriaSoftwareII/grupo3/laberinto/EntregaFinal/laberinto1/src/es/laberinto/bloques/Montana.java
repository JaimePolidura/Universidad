package es.laberinto.bloques;

import es.laberinto.entides.Alfombra;
import es.laberinto.entides.Entidad;

import java.util.List;

public final class Montana extends Bloque {
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
        return List.of(Alfombra.class);
    }

    public static Montana crear() {
        return new Montana();
    }
}
