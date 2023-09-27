package es.laberinto.modelo.entides;

import es.laberinto.modelo.Posicion;

public abstract class Entidad {
    private Posicion posicion;
    private Entidad entidadSobreLaQueEstoyMontado;
    private Entidad entidadMontadaSobreMi;

    private double bufferMovimeintoAnteriorTurno;
    private Posicion posicionDondeQuiereMoverseAnteriorTurno;

    public abstract boolean puedeMontarseEnOtraEntidad();
    public abstract boolean otraEntidadPuedeMontarse();

    public void desmontarme() {
        if(this.entidadSobreLaQueEstoyMontado != null){
            this.entidadSobreLaQueEstoyMontado.entidadMontadaSobreMi = null;
            this.entidadSobreLaQueEstoyMontado = null;
        }
    }

    public void montarme(Entidad entidadAMontarme) {
        if(entidadAMontarme.mePuedoMontar()){
            entidadAMontarme.entidadMontadaSobreMi = this;
            this.entidadSobreLaQueEstoyMontado = entidadAMontarme;

            entidadAMontarme.posicionDondeQuiereMoverseAnteriorTurno = null;
            entidadAMontarme.bufferMovimeintoAnteriorTurno = 0;
        }
    }

    public void setPosicion(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
        this.posicionDondeQuiereMoverseAnteriorTurno = null;
        this.bufferMovimeintoAnteriorTurno = 0;

        if(estoyMontadoSobreEntidad()){
            this.entidadSobreLaQueEstoyMontado.setPosicion(nuevaPosicion);
        }
    }

    public boolean estoyMontadoSobreEntidad() {
        return this.entidadSobreLaQueEstoyMontado != null;
    }

    public boolean otraEntidadEstaMontada() {
        return this.entidadMontadaSobreMi != null;
    }

    public boolean mePuedoMontar() {
        return this.otraEntidadPuedeMontarse() && !otraEntidadEstaMontada();
    }

    public Entidad getEntidadMontadaSobreMi() {
        return entidadMontadaSobreMi;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public Entidad getEntidadSobreLaQueEstoyMontado() {
        return entidadSobreLaQueEstoyMontado;
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

    public void setBufferMovimeintoAnteriorTurno(double bufferMovimeintoAnteriorTurno) {
        this.bufferMovimeintoAnteriorTurno = bufferMovimeintoAnteriorTurno;
    }

    public void setPosicionDondeQuiereMoverseAnteriorTurno(Posicion posicionDondeQuiereMoverseAnteriorTurno) {
        this.posicionDondeQuiereMoverseAnteriorTurno = posicionDondeQuiereMoverseAnteriorTurno;
    }
}
