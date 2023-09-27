package es.laberinto.bloques;

import es.laberinto.entides.Barca;
import es.laberinto.entides.Entidad;

import java.util.List;

public final class AguaTurbulenta extends Bloque {
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
        return List.of(Barca.class);
    }

    public static AguaTurbulenta crear() {
        return new AguaTurbulenta();
    }
}
