package es.jaime.controlador;

import es.jaime.vista.RederizadorHabitacion;

import java.util.Scanner;

public final class Mundo {
    private final RederizadorHabitacion rederizadorHabitacion;
    private final Habitacion habitacion;

    public Mundo(int numeroDeGatos, int tamano, double[] probabilidadesSuciedad) {
        this.habitacion = new Habitacion(numeroDeGatos, tamano, probabilidadesSuciedad);
        this.rederizadorHabitacion = new RederizadorHabitacion();
    }

    public void empezar() {
        do {
            habitacion.moverGatos();
            habitacion.ensuciar();
            rederizadorHabitacion.renderizar(habitacion);
        }while (quiereSeguir());
    }

    private boolean quiereSeguir(){
        Scanner entrada = new Scanner(System.in);
        return !entrada.nextLine().equalsIgnoreCase("f");
    }
}
