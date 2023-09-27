package es.jaime;

import es.jaime.controlador.Mundo;

public final class Main {
    public static void main(String[] args) {
        new Mundo(3, 20, new double[]{0.1, 0.2, 0.7, 0.9})
                .empezar();
    }
}
