package es.laberinto.modelo.entides;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.Posicion;
import es.laberinto.vista.SePuedeRenderizar;

public final class Caballo extends Entidad implements SePuedeRenderizar {
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
        return "CCC";
    }

    public static Caballo crear(Posicion posicion) {
        Caballo caballo = new Caballo();
        caballo.setPosicion(posicion);
        return caballo;
    }
}
