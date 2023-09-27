package es.laberinto.modelo.bloques;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.entides.Entidad;
import es.laberinto.vista.SePuedeRenderizar;

public final class Suelo extends Bloque implements SePuedeRenderizar {
    @Override
    public double velocidad() {
        return 1;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return true;
    }

    @Override
    public String getDibujo(Mundo mundo) {
        return "___";
    }

    public static Suelo crear(){
        return new Suelo();
    }
}
