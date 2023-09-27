package es.laberinto.entides;

import es.laberinto.Mundo;
import es.laberinto.entides.Entidad;
import es.laberinto.utils.Posicion;

public final class Personaje extends Entidad {
    public Personaje(Mundo mundo) {
        super(mundo);
    }

    @Override
    public boolean puedeMontarseEnOtraEntidad() {
        return true;
    }

    @Override
    public boolean otraEntidadPuedeMontarse() {
        return false;
    }

    public static Personaje crear(Mundo mundo, Posicion posicion) {
        Personaje personaje = new Personaje(mundo);
        personaje.setPosicionActual(posicion);

        return personaje;
    }
}
