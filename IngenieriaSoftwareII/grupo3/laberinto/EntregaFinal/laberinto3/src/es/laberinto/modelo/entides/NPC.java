package es.laberinto.modelo.entides;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.Direccion;
import es.laberinto.modelo.Posicion;
import es.laberinto.modelo.Vector;
import es.laberinto.vista.SePuedeRenderizar;

public final class NPC extends Entidad implements SeMueveSolo, SePuedeRenderizar {
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

    @Override
    public String getDibujo(Mundo mundo) {
        return "NPC";
    }

    public static NPC crear(Posicion posicion) {
        NPC npc = new NPC();
        npc.setPosicion(posicion);
        return npc;
    }
}
