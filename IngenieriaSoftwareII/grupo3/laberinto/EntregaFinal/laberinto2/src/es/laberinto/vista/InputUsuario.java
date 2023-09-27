package es.laberinto.vista;

import java.util.Scanner;

public final class InputUsuario {
    private final Scanner scanner = new Scanner(System.in);

    public String leer() {
        return this.scanner.next();
    }
}
