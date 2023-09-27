package es.laberinto.modelo.entides;

import es.laberinto.modelo.Direccion;
import es.laberinto.modelo.Posicion;
import es.laberinto.modelo.Vector;

public final class NPC extends Entidad implements SeMueveSolo {
    @Override
    public boolean puedeMontarseEnOtraEntidad() {
        return false;
    }

    @Override
    public boolean otraEntidadPuedeMontarse() {
        return false;
    }

    @Override
    public Vector getVectorMovimientoSolo(Entidad entidad) {
        return Direccion.aleatorio().getVector();
    }

    public static NPC crear(Posicion posicion) {
        NPC npc = new NPC();
        npc.setPosicion(posicion);
        return npc;
    }
}
