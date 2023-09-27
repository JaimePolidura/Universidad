package es.laberinto.entides;

import es.laberinto.Mundo;
import es.laberinto.utils.Posicion;

public final class Caballo extends Entidad {
    public Caballo(Mundo mundo) {
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

    public static Caballo crear(Mundo mundo, Posicion posicion) {
        Caballo caballo = new Caballo(mundo);
        caballo.setPosicionActual(posicion);
        return caballo;
    }
}
