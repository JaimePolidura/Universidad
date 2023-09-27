package es.laberinto.bloques;

import es.laberinto.utils.Posicion;
import es.laberinto.entides.Entidad;

import java.util.List;

public abstract class Bloque {
    private Posicion posicion;

    public abstract double velocidad();
    public abstract boolean todasLasEntidadesPuedenTransitar();
    public abstract List<Class<? extends Entidad>> soloTransitableCon();

    public boolean puedeTransitar(Entidad entidad) {
        return this.todasLasEntidadesPuedenTransitar()
                || soloTransitableCon().stream().anyMatch(it -> it.equals(entidad.getClass()))
                || soloTransitableCon().stream().anyMatch(it -> entidad.estoyMontadoSobreEntidad()
                                                                && it.equals(entidad.getEntidadSobreLaQueEstoyMontado().getClass()));
    }
}
