package es.laberinto.modelo.entides;

import es.laberinto.modelo.Posicion;

public final class Personaje extends Entidad {
    @Override
    public boolean puedeMontarseEnOtraEntidad() {
        return true;
    }

    @Override
    public boolean otraEntidadPuedeMontarse() {
        return false;
    }

    public static Personaje crear(Posicion posicion) {
        Personaje personaje = new Personaje();
        personaje.setPosicion(posicion);

        return personaje;
    }
}
