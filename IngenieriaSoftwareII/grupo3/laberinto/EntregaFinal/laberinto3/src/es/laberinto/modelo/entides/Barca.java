package es.laberinto.modelo.entides;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.Posicion;
import es.laberinto.vista.SePuedeRenderizar;

public final class Barca extends Entidad implements SePuedeRenderizar {
    @Override
    public boolean puedeMontarseEnOtraEntidad() {
        return false;
    }

    @Override
    public boolean otraEntidadPuedeMontarse() {
        return true;
    }

    @Override
    public String getDibujo(Mundo mundo) {
        return "BBB";
    }

    public static Barca crear(Posicion posicion) {
        Barca barca = new Barca();
        barca.setPosicion(posicion);

        return barca;
    }
}
