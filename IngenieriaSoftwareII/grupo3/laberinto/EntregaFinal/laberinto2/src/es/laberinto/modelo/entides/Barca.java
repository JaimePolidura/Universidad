package es.laberinto.modelo.entides;

import es.laberinto.modelo.Posicion;

public final class Barca extends Entidad {
    @Override
    public boolean puedeMontarseEnOtraEntidad() {
        return false;
    }

    @Override
    public boolean otraEntidadPuedeMontarse() {
        return true;
    }

    public static Barca crear(Posicion posicion) {
        Barca barca = new Barca();
        barca.setPosicion(posicion);

        return barca;
    }
}
