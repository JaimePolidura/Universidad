package es.laberinto.bloques;

import es.laberinto.entides.Entidad;
import es.laberinto.entides.Personaje;

import java.util.List;

public final class Arena extends Bloque{
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
        return List.of(Personaje.class);
    }

    public static Arena crear() {
        return new Arena();
    }
}
