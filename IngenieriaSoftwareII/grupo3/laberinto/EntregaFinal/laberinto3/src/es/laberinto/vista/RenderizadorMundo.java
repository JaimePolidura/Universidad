package es.laberinto.vista;

import es.laberinto.controlador.Mundo;
import es.laberinto.modelo.bloques.*;
import es.laberinto.modelo.entides.*;
import es.laberinto.modelo.Posicion;

public final class RenderizadorMundo {
    public void renderizar(Mundo mundo) {
        for(int i = 0; i<mundo.getBloques().length; i++){
            for(int j = 0; j<mundo.bloques[i].length;j++){
                Entidad entidad = mundo.getEntidad(Posicion.crear(j,i));
                Bloque bloque = mundo.bloques[i][j];

                if(entidad instanceof SePuedeRenderizar entidadRenderizable)
                    System.out.print(entidadRenderizable.getDibujo(mundo));
                else if(bloque instanceof SePuedeRenderizar bloqueRenderizable)
                    System.out.print(bloqueRenderizable.getDibujo(mundo));
            }

            System.out.println();
        }
    }
}
