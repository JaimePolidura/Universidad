package es.laberinto.controlador;

import es.laberinto.modelo.Posicion;
import es.laberinto.modelo.Vector;
import es.laberinto.modelo.bloques.Bloque;
import es.laberinto.modelo.entides.Entidad;

public final class MovedorEntidadesMundo {
    private final Mundo mundo;

    public MovedorEntidadesMundo(Mundo mundo) {
        this.mundo = mundo;
    }

    public void mover(Entidad entidad, Vector vector) {
        Posicion posicionAPartirDeVector = entidad.getPosicion().nuevaPosicionAPartirDe(vector);

        if(!this.entidadSePuedeMoverALaPosicion(entidad, posicionAPartirDeVector))
            return;

        Bloque bloqueEnPosicionAPartirDeVector = mundo.getBloque(posicionAPartirDeVector);
        double velocidadSiguienteBloque = bloqueEnPosicionAPartirDeVector.velocidad();
        if(velocidadSiguienteBloque >= 1){
            Posicion nuevaPosicion = entidad.getPosicion().nuevaPosicionAPartirDe(vector.aumentarEn(velocidadSiguienteBloque));

            if(!entidadSePuedeMoverALaPosicion(entidad, nuevaPosicion)){
                return;
            }

            actualizarPosicionEntidad(entidad, nuevaPosicion);
            return;
        }

        if(!posicionAPartirDeVector.mismaPosicion(entidad.getPosicionDondeQuiereMoverseAnteriorTurno())) {
            entidad.setBufferMovimeintoAnteriorTurno(0);
        }

        entidad.setBufferMovimeintoAnteriorTurno(entidad.getBufferMovimeintoAnteriorTurno() + velocidadSiguienteBloque);
        entidad.setPosicionDondeQuiereMoverseAnteriorTurno(posicionAPartirDeVector);

        if(entidad.getBufferMovimeintoAnteriorTurno() >= 1){
            actualizarPosicionEntidad(entidad, posicionAPartirDeVector);
        }

    }

    private void actualizarPosicionEntidad(Entidad entidad, Posicion nuevaPosicion) {
        entidad.setPosicion(nuevaPosicion);

        Entidad entidadEnNuevaPosicion = mundo.getEntidad(nuevaPosicion);
        if(entidadEnNuevaPosicion != null && entidadEnNuevaPosicion.mePuedoMontar()){
            entidad.montarme(entidadEnNuevaPosicion);
        }
    }

    private boolean entidadSePuedeMoverALaPosicion(Entidad entidad, Posicion posicion) {
        if(posicionFueraDeLosLimites(posicion) || entidad.otraEntidadEstaMontada())
            return false;

        Bloque siguienteBloque = mundo.getBloque(posicion);
        if((entidad.estoyMontadoSobreEntidad() && !siguienteBloque.puedeTransitar(entidad.getEntidadSobreLaQueEstoyMontado())) ||
                (!entidad.estoyMontadoSobreEntidad() && !siguienteBloque.puedeTransitar(entidad)))
            return false;

        return !hayColisionConEntidadNoMontable(posicion);
    }


    private boolean hayColisionConEntidadNoMontable(Posicion posicion) {
        Entidad entidad = mundo.getEntidad(posicion);

        return entidad != null && !entidad.mePuedoMontar();
    }

    private boolean posicionFueraDeLosLimites(Posicion posicion) {
        return posicion.x() < 0 ||
                posicion.y() < 0 ||
                posicion.y() + 1 > mundo.getLargo() ||
                posicion.x() + 1 > mundo.getAncho();
    }
}
