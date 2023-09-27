package es.laberinto.modelo.bloques;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.entides.Alfombra;
import es.laberinto.modelo.entides.Barca;
import es.laberinto.modelo.entides.Entidad;
import es.laberinto.vista.SePuedeRenderizar;

public final class Agua extends Bloque implements SePuedeRenderizar {
    @Override
    public double velocidad() {
        return 1;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return entidad.getClass() == Barca.class || entidad.getClass() == Alfombra.class;
    }

    @Override
    public String getDibujo(Mundo mundo) {
        return "~~~";
    }

    public static Agua crear() {
        return new Agua();
    }
}
