package es.laberinto.modelo.entides;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.Posicion;
import es.laberinto.vista.SePuedeRenderizar;

public final class Alfombra extends Entidad implements SePuedeRenderizar {
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
        return "AAA";
    }

    public static Alfombra crear(Posicion posicion) {
        Alfombra alfombra = new Alfombra();
        alfombra.setPosicion(posicion);

        return alfombra;
    }
}
