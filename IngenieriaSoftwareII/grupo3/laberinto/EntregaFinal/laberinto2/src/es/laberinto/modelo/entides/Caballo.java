package es.laberinto.modelo.entides;

import es.laberinto.modelo.Posicion;

public final class Caballo extends Entidad {
    @Override
    public boolean puedeMontarseEnOtraEntidad() {
        return false;
    }

    @Override
    public boolean otraEntidadPuedeMontarse() {
        return true;
    }

    public static Caballo crear(Posicion posicion) {
        Caballo caballo = new Caballo();
        caballo.setPosicion(posicion);
        return caballo;
    }
}
