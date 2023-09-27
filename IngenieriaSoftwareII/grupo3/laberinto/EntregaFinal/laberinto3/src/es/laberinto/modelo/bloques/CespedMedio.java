package es.laberinto.modelo.bloques;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.entides.Alfombra;
import es.laberinto.modelo.entides.Entidad;
import es.laberinto.modelo.entides.Personaje;
import es.laberinto.vista.SePuedeRenderizar;

public final class CespedMedio extends Bloque implements SePuedeRenderizar {
    @Override
    public double velocidad() {
        return 1;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return entidad.getClass() == Personaje.class || entidad.getClass() == Alfombra.class;
    }

    @Override
    public String getDibujo(Mundo mundo) {
        return ":::";
    }

    public static CespedMedio crear() {
        return new CespedMedio();
    }
}
