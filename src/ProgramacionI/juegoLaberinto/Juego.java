package ProgramacionI.juegoLaberinto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private final List<String> teclasValidas = Arrays.asList("w","a","s","d","e", "g");
    private Laberinto labetinto;
    private Jugador jugador;

    public Juego() {
        this.jugador = new Jugador();
        this.labetinto = new Laberinto();
    }

    public void empezar () {
        labetinto.dibujar(jugador);
        escucharAlTeclado();
    }

    public void escucharAlTeclado () {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("   ");
            System.out.print("Introduce una tecla (wasd, g: gema,e: salir): ");
            String letra = scanner.next();

            if (!esLaTeclaValida(letra)) {
                System.out.println("Introduce una tecla valida");
                continue;
            }
            if(letra.equalsIgnoreCase("e")){
                System.out.println("Has salido del juego");
                System.exit(1);
            }
            if(letra.equalsIgnoreCase("g") && jugador.gemas > 0){
                jugador.gemas--;

                labetinto.elJugadorHaTomadoGema = true;
            }

            modificarPosicionJugador(letra);
        }
    }

    private boolean esLaTeclaValida(String letra) {
        return teclasValidas.stream().anyMatch(tecla -> tecla.equalsIgnoreCase(letra));
    }

    private void modificarPosicionJugador (String tecla) {
        int posX = jugador.x;
        int posY = jugador.y;

        switch (tecla.toLowerCase()){
            case "w": posX--; break;
            case "s": posX++; break;
            case "a": posY--; break;
            case "d": posY++; break;
        }

        if((posX < 0 || posX > labetinto.maxX) || (posY < 0 || posY > labetinto.maxY)) {
            posX = jugador.x;
            posY = jugador.y;
        }
        if(hayPared(posX, posY)){
            posX = jugador.x;
            posY = jugador.y;
        }

        jugador.x = posX;
        jugador.y = posY;
        labetinto.dibujar(jugador);
    }

    private boolean hayPared(int x, int y) {
        return this.labetinto.laberinto[x][y] == 1;
    }
}
