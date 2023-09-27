package es.laberinto.entides;

import es.laberinto.Mundo;
import es.laberinto.utils.Posicion;

public final class Alfombra extends Entidad {
    public Alfombra(Mundo mundo) {
        super(mundo);
    }

    @Override
    public boolean puedeMontarseEnOtraEntidad() {
        return false;
    }

    @Override
    public boolean otraEntidadPuedeMontarse() {
        return true;
    }

    public static Alfombra crear(Mundo mundo, Posicion posicion) {
        Alfombra alfombra = new Alfombra(mundo);
        alfombra.setPosicionActual(posicion);

        return alfombra;
    }
}
