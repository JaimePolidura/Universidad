package es.laberinto;

import es.laberinto.bloques.*;
import es.laberinto.entides.*;
import es.laberinto.utils.Posicion;

public final class RenderizadorMundo {
    public void renderizar(Mundo mundo) {
        for(int i = 0; i<mundo.bloques.length; i++){
            for(int j = 0; j<mundo.bloques[i].length;j++){
                Entidad entidad = mundo.getEntidad(Posicion.crear(j,i));

                if(entidad != null){
                    if(entidad instanceof Alfombra) System.out.print("AAA");
                    else if(entidad instanceof Barca) System.out.print("BBB");
                    else if(entidad instanceof Caballo) System.out.print("CCC");
                    else if(entidad instanceof NPC) System.out.print("NPC");
                    else if(entidad instanceof Personaje) System.out.print("ZZZ");

                    continue;
                }

                if (mundo.bloques[i][j]instanceof Agua)	{System.out.print("~~~");}
                else if (mundo.bloques[i][j]instanceof AguaTurbulenta)	{System.out.print("888");}
                else if (mundo.bloques[i][j]instanceof Arena)	{System.out.print("...");}
                else if (mundo.bloques[i][j]instanceof CespedBajo)	{System.out.print(".:.");}
                else if (mundo.bloques[i][j]instanceof CespedMedio)	{System.out.print(":::");}
                else if (mundo.bloques[i][j]instanceof CespedAlto)	{System.out.print("///");}
                else if (mundo.bloques[i][j]instanceof Montana)	{System.out.print("^^^");}
                else if (mundo.bloques[i][j]instanceof Pared)	{System.out.print("[ ]");}
                else if (mundo.bloques[i][j]instanceof Suelo)	{System.out.print("___");}
            }
            System.out.println();
        }
    }
}
