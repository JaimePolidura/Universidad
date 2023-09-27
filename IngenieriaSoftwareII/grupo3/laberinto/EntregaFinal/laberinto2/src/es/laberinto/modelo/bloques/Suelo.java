package es.laberinto.modelo.bloques;

import es.laberinto.modelo.entides.Entidad;

public final class Suelo extends Bloque {
    @Override
    public double velocidad() {
        return 1;
    }

    @Override
    public boolean puedeTransitar(Entidad entidad) {
        return true;
    }

    public static Suelo crear(){
        return new Suelo();
    }
}
