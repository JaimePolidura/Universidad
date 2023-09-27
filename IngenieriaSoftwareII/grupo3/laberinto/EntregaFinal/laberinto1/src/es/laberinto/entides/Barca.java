package es.laberinto.entides;

import es.laberinto.Mundo;
import es.laberinto.utils.Posicion;

public final class Barca extends Entidad {
    public Barca(Mundo mundo) {
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

    public static Barca crear(Mundo mundo, Posicion posicion) {
        Barca barca = new Barca(mundo);
        barca.setPosicionActual(posicion);

        return barca;
    }
}
