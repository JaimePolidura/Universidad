package es.laberinto.controlador;

import es.laberinto.modelo.bloques.Bloque;
import es.laberinto.modelo.entides.Entidad;
import es.laberinto.modelo.entides.Personaje;
import es.laberinto.modelo.entides.SeMueveSolo;
import es.laberinto.modelo.Direccion;
import es.laberinto.modelo.Posicion;
import es.laberinto.modelo.Vector;
import es.laberinto.vista.InputUsuario;
import es.laberinto.vista.RenderizadorMundo;

import java.util.List;

public final class Mundo {
    private final MovedorEntidadesMundo movedorEntidadesMundo;
    private final RenderizadorMundo renderizadorMundo;
    private final InputUsuario inputUsuario;

    private final List<Entidad> entidades;
    public final Bloque[][] bloques;
    private final Personaje personaje;
    private int tiempoTranscurrido;

    public Mundo(List<Entidad> entidades, Personaje personaje, Bloque[][] bloques, RenderizadorMundo renderizadorMundo) {
        this.entidades = entidades;
        this.personaje = personaje;
        this.bloques = bloques;
        this.movedorEntidadesMundo = new MovedorEntidadesMundo(this);
        this.renderizadorMundo = renderizadorMundo;
        this.inputUsuario = new InputUsuario();
        this.entidades.add(personaje);
    }

    public void iniciar() {
        renderizadorMundo.renderizar(this);

        while(true){
            leerInputUsuarioYAplicar();
            tiempoTranscurrido++;
            actualizarEntidades();
            renderizadorMundo.renderizar(this);
        }
    }

    private void leerInputUsuarioYAplicar() {
        switch (this.inputUsuario.leer()) {
            case "w" -> moverPersonaje(Direccion.ARRIBA);
            case "a" -> moverPersonaje(Direccion.IZQUIERDA);
            case "d" -> moverPersonaje(Direccion.DERECHA);
            case "s" -> moverPersonaje(Direccion.ABAJO);
            case "r" -> desmontarsePersonaje();
            case "e" -> System.exit(1);
        }
    }

    public void moverPersonaje(Direccion direccion) {
        movedorEntidadesMundo.mover(personaje, direccion.getVector());
    }

    public void desmontarsePersonaje() {
        Bloque bloqueDondeDesmontarse = getBloque(this.personaje.getPosicion());

        if(bloqueDondeDesmontarse.puedeTransitar(this.personaje)){
            this.personaje.desmontarme();
        }
    }

    public Bloque getBloque(Posicion posicion) {
        return this.bloques[posicion.y()][posicion.x()];
    }

    public Bloque[][] getBloques() {
        return this.bloques;
    }

    public Entidad getEntidad(Posicion posicion) {
        return this.entidades.stream()
                .filter(it -> it.getPosicion().mismaPosicion(posicion))
                .findFirst()
                .orElse(null);
    }

    private void actualizarEntidades() {
        for(Entidad entidad : this.entidades){
            if(!(entidad instanceof SeMueveSolo seMueveSoloEntidad) || entidad.otraEntidadEstaMontada())
                continue;

            Vector vectorMovimientoSolo = seMueveSoloEntidad.getVectorMovimientoSolo(entidad);
            movedorEntidadesMundo.mover(entidad, vectorMovimientoSolo);
        }
    }

    public int getAncho() {
        return this.bloques[0].length;
    }

    public int getLargo() {
        return this.bloques.length;
    }

    public List<Entidad> getEntidades() {
        return entidades;
    }
}
