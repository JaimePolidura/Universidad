package ProgramacionII.proyectofinal;

import ProgramacionII.proyectofinal.figuras.Figura;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase donde guardaremos todo el listado de figuras que vata introduciendo el usuario.
 * Con esta clase tambien la podremos utilizar para deshcaer y rehacer
 */
public final class AlmacenamientoFiguras {
    private final List<Figura> figuras;
    private int indexFigura;

    public AlmacenamientoFiguras() {
        this.figuras = new ArrayList<>();
        this.indexFigura = -1;
    }

    public List<Figura> getFiguras() {
        return figuras.subList(0, indexFigura + 1);
    }

    public void addFigura (Figura figura) {
        borrarPosicionesArray(indexFigura + 1);
        this.figuras.add(figura);
        indexFigura++;
    }

    public boolean isEmpty () {
        return figuras.isEmpty();
    }

    public void deshacer () {
        if(indexFigura > -1){
            indexFigura--;
        }
    }

    public void rehacer () {
        if((indexFigura + 1) < figuras.size()){
            indexFigura++;
        }
    }

    private void borrarPosicionesArray(int start) {
        if (figuras.size() > start) {
            figuras.subList(start, figuras.size()).clear();
        }
    }
}
