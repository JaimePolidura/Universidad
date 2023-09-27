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
import java.util.Scanner;

public final class Mundo {
    private final RenderizadorMundo renderizadorMundo;
    private final InputUsuario inputUsuario;

    public final List<Entidad> entidades;
    public final Bloque[][] bloques;
    private int tiempoTranscurrido;
    private final Scanner scanner;
    public Personaje personaje;

    public Mundo(List<Entidad> entidades, Bloque[][] bloques, RenderizadorMundo renderizadorMundo) {
        this.entidades = entidades;
        this.bloques = bloques;
        this.scanner = new Scanner(System.in);
        this.renderizadorMundo = new RenderizadorMundo();
        this.personaje = this.getPersonaje(entidades);
        this.inputUsuario = new InputUsuario();
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

    private Personaje getPersonaje(List<Entidad> entidades) {
        for (Entidad entidad : entidades) {
            if(entidad instanceof Personaje personaje) {
                return personaje;
            }
        }

        return null;
    }

    public void moverPersonaje(Direccion direccion) {
        this.mover(this.personaje, direccion.getVector());
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

    public Entidad getEntidad(Posicion posicion) {
        return this.entidades.stream()
                .filter(it -> it.getPosicion().mismaPosicion(posicion))
                .findFirst()
                .orElse(null);
    }

    private boolean posicionFueraDeLosLimites(Posicion posicion) {
        return posicion.x() < 0 ||
               posicion.y() < 0 ||
               posicion.y() + 1 > this.getLargo() ||
               posicion.x() + 1 > this.getAncho();
    }

    private void actualizarEntidades() {
        for(Entidad entidad : this.entidades){
            if(!(entidad instanceof SeMueveSolo seMueveSoloEntidad) || entidad.otraEntidadEstaMontada())
                continue;

            Vector vectorMovimientoSolo = seMueveSoloEntidad.getVectorMovimientoSolo(entidad);
            this.mover(entidad, vectorMovimientoSolo);
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

    private boolean mover(Entidad entidad, Vector vector) {
        Posicion posicionAPartirDeVector = entidad.getPosicion().nuevaPosicionAPartirDe(vector);

        if(!this.entidadSePuedeMoverALaPosicion(entidad, posicionAPartirDeVector))
            return false;

        Bloque bloqueEnPosicionAPartirDeVector = getBloque(posicionAPartirDeVector);
        double velocidadSiguienteBloque = bloqueEnPosicionAPartirDeVector.velocidad();
        if(velocidadSiguienteBloque >= 1){
            Posicion nuevaPosicion = entidad.getPosicion().nuevaPosicionAPartirDe(vector.aumentarEn(velocidadSiguienteBloque));

            if(!this.entidadSePuedeMoverALaPosicion(entidad, nuevaPosicion)) return false;

            actualizarPosicionEntidad(entidad, nuevaPosicion);

            return true;
        }

        if(!posicionAPartirDeVector.mismaPosicion(entidad.getPosicionDondeQuiereMoverseAnteriorTurno())) {
            entidad.setBufferMovimeintoAnteriorTurno(0);
        }

        entidad.setBufferMovimeintoAnteriorTurno(entidad.getBufferMovimeintoAnteriorTurno() + velocidadSiguienteBloque);
        entidad.setPosicionDondeQuiereMoverseAnteriorTurno(posicionAPartirDeVector);

        if(entidad.getBufferMovimeintoAnteriorTurno() >= 1){
            actualizarPosicionEntidad(entidad, posicionAPartirDeVector);
            return true;
        }

        return false;
    }

    private void actualizarPosicionEntidad(Entidad entidad, Posicion nuevaPosicion) {
        entidad.setPosicion(nuevaPosicion);

        Entidad entidadEnNuevaPosicion = this.getEntidad(nuevaPosicion);
        if(entidadEnNuevaPosicion != null && entidadEnNuevaPosicion.mePuedoMontar()){
            entidad.montarme(entidadEnNuevaPosicion);
        }
    }

    private boolean entidadSePuedeMoverALaPosicion(Entidad entidad, Posicion posicion) {
        if(posicionFueraDeLosLimites(posicion) || entidad.otraEntidadEstaMontada())
            return false;

        Bloque siguienteBloque = this.getBloque(posicion);
        if(!siguienteBloque.puedeTransitar(entidad))
            return false;

        return !hayColisionConEntidadNoMontable(posicion);
    }

    private boolean hayColisionConEntidadNoMontable(Posicion posicion) {
        Entidad entidad = this.getEntidad(posicion);

        return entidad != null && !entidad.mePuedoMontar();
    }
}
