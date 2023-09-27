package es.jaime.controlador;

import es.jaime.modelo.*;

import java.util.ArrayList;
import java.util.List;

public final class Habitacion {
    public final Baldosa[][] baldosas;
    public final List<Gato> gatos;
    public final int tamano;

    public Habitacion(int numeroDeGatos, int tamano, double[] probabilidadesSuciedad) {
        this.gatos = new ArrayList<>(numeroDeGatos);
        this.baldosas = new Baldosa[tamano][tamano];
        this.tamano = tamano;
        this.inicializarMundo(probabilidadesSuciedad, numeroDeGatos);
    }

    public void ensuciar() {
        for (Gato gato : gatos) {
             if(gato.vaAEnsuciar()) {
                 this.baldosas[gato.posicion.x()][gato.posicion.y()].ensuciar();
             }
        }
    }

    public void moverGatos() {
        for (Gato gato : this.gatos) {
            Direccion nuevaDireccion = Direccion.nuevaDireccionAleatoria();
            Posicion nuevaPosicion = gato.posicion.nuevaPosicionAPartirDe(nuevaDireccion);

            if(posicionDentroDeLosLimitesMundo(nuevaPosicion) && noHayColisionesConOtrosGatos(nuevaPosicion)){
                gato.posicion = nuevaPosicion;
            }
        }
    }

    private boolean noHayColisionesConOtrosGatos(Posicion posicion) {
        return this.gatos.stream()
                .noneMatch(gato -> gato.posicion.x() == posicion.x() && gato.posicion.y() == posicion.y());
    }

    private boolean posicionDentroDeLosLimitesMundo(Posicion posicion) {
        return posicion.x() >= 0 && posicion.y() >= 0 && posicion.x() < tamano && posicion.y() < tamano;
    }

    private void inicializarMundo(double[] probabilidadesSuciedad, int numeroGatos) {
        this.crearGatos(numeroGatos);
        this.crearBaldosas(probabilidadesSuciedad);
    }

    private void crearGatos(int numeroGatos) {
        for (int i = 0; i < numeroGatos; i++) {
            Posicion posicionAleatoria = this.getPosicionAleatoriaSinColisiones();

            this.gatos.add(new Gato(posicionAleatoria.x(), posicionAleatoria.y()));
        }
    }

    private Posicion getPosicionAleatoriaSinColisiones() {
        Posicion posicion;

        do {
            posicion = Posicion.aleatorio(0, this.tamano - 1, 0, this.tamano - 1);
        }while (!noHayColisionesConOtrosGatos(posicion)); //Mientras halla colisiones

        return posicion;
    }

    private void crearBaldosas(double[] probabilidadesSuciedad) {
        for(int i = 0; i < this.baldosas.length; i++) {
            for(int j = 0; j < this.baldosas[i].length; j++){
                this.baldosas[i][j] = new Baldosa(calcularNivelSuciedadAleatorio(probabilidadesSuciedad));
            }
        }
    }

    private int calcularNivelSuciedadAleatorio(double[] probabilidadesSuciedad) {
        double probabilidadMugre = Math.random();

        for (int i = Suciedad.MAXIMO_NIVEL; i >= Suciedad.MINIMO_NIVEL; i--) {
            if (probabilidadMugre >= probabilidadesSuciedad[i]) {
                return i;
            }
        }

        return Suciedad.MINIMO_NIVEL;
    }
}
