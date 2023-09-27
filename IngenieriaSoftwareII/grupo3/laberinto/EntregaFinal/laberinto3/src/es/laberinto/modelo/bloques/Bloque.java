package es.laberinto.modelo.bloques;

import es.laberinto.modelo.entides.Entidad;
import es.laberinto.vista.SePuedeRenderizar;

public abstract class Bloque {
    public abstract double velocidad();
    
    public abstract boolean puedeTransitar(Entidad entidad);
}
