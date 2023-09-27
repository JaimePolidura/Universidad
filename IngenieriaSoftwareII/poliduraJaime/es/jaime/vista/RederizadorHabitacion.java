package es.jaime.vista;

import es.jaime.controlador.Habitacion;

public final class RederizadorHabitacion {
    public void renderizar(Habitacion habitacion) {
        borraPantalla();
        imprimeLinea(habitacion);
        imprimirBaldosasYGatos(habitacion);
        imprimeLinea(habitacion);
    }

    private void imprimirBaldosasYGatos(Habitacion habitacion) {
        for (int i = 0; i < habitacion.tamano; i++) {
            for (int j = 0; j < habitacion.tamano; j++) {
                if (hayGatoEnLaPosicion(habitacion, j, i)) {
                    System.out.print(">G<");
                } else {
                    char vistaBaldosa = " .oO#".charAt(habitacion.baldosas[i][j].getSuciedad().getNivel());
                    System.out.print(" " + vistaBaldosa + " ");
                }
            }
            System.out.println();
        }
    }

    private void imprimeLinea(Habitacion habitacion) {
        for (int j = 0; j < habitacion.tamano; j++) {
            System.out.print("===");
        }
        System.out.println();
    }

    private boolean hayGatoEnLaPosicion(Habitacion habitacion, int x, int y) {
        return habitacion.gatos.stream()
                .anyMatch(gato -> gato.posicion.x() == x && gato.posicion.y() == y);
    }

    private void borraPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
