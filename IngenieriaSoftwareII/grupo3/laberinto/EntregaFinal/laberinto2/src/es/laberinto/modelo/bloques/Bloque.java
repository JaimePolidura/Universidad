package es.laberinto.modelo.bloques;

import es.laberinto.modelo.entides.Entidad;

public abstract class Bloque {
    public abstract double velocidad();
    
    public abstract boolean puedeTransitar(Entidad entidad);
}
