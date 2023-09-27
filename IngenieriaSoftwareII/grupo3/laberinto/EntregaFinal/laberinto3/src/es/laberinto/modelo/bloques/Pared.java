package es.laberinto.modelo.bloques;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.entides.Entidad;
import es.laberinto.vista.SePuedeRenderizar;

public final class Pared extends Bloque implements SePuedeRenderizar {
    @Override
    public double velocidad() {
        return 0;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return false;
    }

    @Override
    public String getDibujo(Mundo mundo) {
        return "[ ]";
    }

    public static Pared crear() {
        return new Pared();
    }
}
