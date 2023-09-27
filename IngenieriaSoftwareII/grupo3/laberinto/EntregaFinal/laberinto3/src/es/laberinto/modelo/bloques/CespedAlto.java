package es.laberinto.modelo.bloques;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.entides.Alfombra;
import es.laberinto.modelo.entides.Caballo;
import es.laberinto.modelo.entides.Entidad;
import es.laberinto.vista.SePuedeRenderizar;

public final class CespedAlto extends Bloque implements SePuedeRenderizar {
    @Override
    public double velocidad() {
        return 2;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return entidad.getClass() == Caballo.class || entidad.getClass() == Alfombra.class;
    }

    @Override
    public String getDibujo(Mundo mundo) {
        return "///";
    }

    public static CespedAlto crear() {
        return new CespedAlto();
    }
}
