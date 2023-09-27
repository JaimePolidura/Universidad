package es.laberinto.entides;

import es.laberinto.Mundo;
import es.laberinto.utils.Direccion;
import es.laberinto.utils.Posicion;
import es.laberinto.utils.Vector;

import javax.swing.*;

public final class NPC extends Entidad implements SeMueveSolo {
    public NPC(Mundo mundo) {
        super(mundo);
    }

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

    public static NPC crear(Mundo mundo, Posicion posicion) {
        NPC npc = new NPC(mundo);
        npc.setPosicionActual(posicion);
        return npc;
    }
}
