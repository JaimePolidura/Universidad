package es.laberinto.modelo.entides;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.Posicion;
import es.laberinto.vista.SePuedeRenderizar;

public final class Personaje extends Entidad implements SePuedeRenderizar {
    @Override
    public boolean puedeMontarseEnOtraEntidad() {
        return true;
    }

    @Override
    public boolean otraEntidadPuedeMontarse() {
        return false;
    }

    @Override
    public String getDibujo(Mundo mundo) {
        return "ZZZ";
    }

    public static Personaje crear(Posicion posicion) {
        Personaje personaje = new Personaje();
        personaje.setPosicion(posicion);

        return personaje;
    }
}
