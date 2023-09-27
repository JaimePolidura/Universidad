package es.laberinto.entides;

import es.laberinto.Mundo;
import es.laberinto.utils.Posicion;
import es.laberinto.bloques.Bloque;
import es.laberinto.utils.Vector;

public abstract class Entidad {
    private Mundo mundo;
    private Posicion posicionActual;
    private Entidad entidadSobreLaQueEstoyMontado;
    private Entidad entidadMontadaSobreMi;

    private double bufferMovimeintoAnteriorTurno;
    private Posicion posicionDondeQuiereMoverseAnteriorTurno;

    public abstract boolean puedeMontarseEnOtraEntidad();
    public abstract boolean otraEntidadPuedeMontarse();

    public Entidad(Mundo mundo) {
        this.mundo = mundo;
    }

    public boolean estoyMontadoSobreEntidad() {
        return this.entidadSobreLaQueEstoyMontado != null;
    }

    public boolean otraEntidadEstaMontada() {
        return this.entidadMontadaSobreMi != null;
    }

    public void desmontarme() {
        if(this.entidadSobreLaQueEstoyMontado != null){
            this.entidadSobreLaQueEstoyMontado.entidadMontadaSobreMi = null;
            this.entidadSobreLaQueEstoyMontado = null;
        }
    }

    public void montarme(Entidad entidadAMontarme) {
        entidadAMontarme.entidadMontadaSobreMi = this;
        this.entidadSobreLaQueEstoyMontado = entidadAMontarme;

        entidadAMontarme.posicionDondeQuiereMoverseAnteriorTurno = null;
        entidadAMontarme.bufferMovimeintoAnteriorTurno = 0;
    }

    public boolean mover(Vector vectorDireccion) {
        Posicion nuevaPosicion = this.posicionActual.nuevaPosicionAPartirDe(vectorDireccion);

        if(!mePuedoMoverALaPosicion(nuevaPosicion))
            return false;

        double velocidadSiguienteBloque = mundo.getBloque(nuevaPosicion).velocidad();
        if(velocidadSiguienteBloque >= 1){
            nuevaPosicion = posicionActual.nuevaPosicionAPartirDe(vectorDireccion.aumentarEn(velocidadSiguienteBloque));

            if(!mePuedoMoverALaPosicion(nuevaPosicion)) return false;

            actualizarPosicion(nuevaPosicion);

            return true;
        }

        if(!nuevaPosicion.mismaPosicion(this.posicionDondeQuiereMoverseAnteriorTurno)) {
            this.bufferMovimeintoAnteriorTurno = 0;
        }

        this.bufferMovimeintoAnteriorTurno += velocidadSiguienteBloque;
        this.posicionDondeQuiereMoverseAnteriorTurno = nuevaPosicion;

        if(this.bufferMovimeintoAnteriorTurno >= 1){
            actualizarPosicion(nuevaPosicion);
            return true;
        }

        return false;
    }

    private void actualizarPosicion(Posicion nuevaPosicion) {
        this.posicionActual = nuevaPosicion;
        this.posicionDondeQuiereMoverseAnteriorTurno = null;
        this.bufferMovimeintoAnteriorTurno = 0;

        Entidad entidadEnLaNuevaPosicion = mundo.getEntidad(nuevaPosicion);
        if(entidadEnLaNuevaPosicion != null && entidadEnLaNuevaPosicion.mePuedoMontar()){
            montarme(entidadEnLaNuevaPosicion);
        }
        if(estoyMontadoSobreEntidad()){
            this.entidadSobreLaQueEstoyMontado.setPosicionActual(nuevaPosicion);
        }
    }

    private boolean mePuedoMoverALaPosicion(Posicion posicion) {
        if(mundo.posicionFueraDeLosLimites(posicion) || otraEntidadEstaMontada())
            return false;

        Bloque siguienteBloque = mundo.getBloque(posicion);
        if(!siguienteBloque.puedeTransitar(this))
            return false;

        Entidad entidadEnElBloqueDireccion = mundo.getEntidad(posicion);

        return !hayEntidadYNoSePuedeMontar(entidadEnElBloqueDireccion);
    }

    private boolean hayEntidadYNoSePuedeMontar(Entidad entidad) {
        return entidad != null && (
                        !entidad.otraEntidadPuedeMontarse() ||
                        entidad.otraEntidadEstaMontada());
    }

    private boolean mePuedoMontar() {
        return this.otraEntidadPuedeMontarse() && !otraEntidadEstaMontada();
    }

    public Entidad getEntidadMontadaSobreMi() {
        return entidadMontadaSobreMi;
    }

    public Posicion getPosicionActual() {
        return posicionActual;
    }

    public Entidad getEntidadSobreLaQueEstoyMontado() {
        return entidadSobreLaQueEstoyMontado;
    }

    public Mundo getMundo() {
        return this.mundo;
    }

    public void setPosicionActual(Posicion posicionActual) {
        this.posicionActual = new Posicion(posicionActual.x(), posicionActual.y());
    }

    public double getBufferMovimeintoAnteriorTurno() {
        return bufferMovimeintoAnteriorTurno;
    }

    public void setEntidadMontadaSobreMi(Entidad entidadMontadaSobreMi) {
        this.entidadMontadaSobreMi = entidadMontadaSobreMi;
    }

    public void setEntidadSobreLaQueEstoyMontado(Entidad entidadSobreLaQueEstoyMontado) {
        this.entidadSobreLaQueEstoyMontado = entidadSobreLaQueEstoyMontado;
    }

    public Posicion getPosicionDondeQuiereMoverseAnteriorTurno() {
        return posicionDondeQuiereMoverseAnteriorTurno;
    }
}
