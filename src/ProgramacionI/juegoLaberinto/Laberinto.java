package ProgramacionI.juegoLaberinto;

import java.util.Arrays;

import static ProgramacionI.juegoLaberinto.Laberinto.Material.*;

public class Laberinto {
    boolean elJugadorHaTomadoGema;
    int gemaX;
    int gemaY;
    int[][] laberinto;
    int maxX;
    int maxY;

    public Laberinto () {
        this.laberinto = contruirLaberinto();
        this.maxX = laberinto.length;
        this.maxY = laberinto[0].length;
        this.gemaX = 0;
        this.gemaY = 3;
        this.elJugadorHaTomadoGema = false;
    }

    private int[][] contruirLaberinto () {
        return new int[][] {
                {0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1},
                {0,1,0,1,1,1,1,0,1,0,0,0,0,0,1,0,0,0,1,1,1,0,1,0,0,1,0,1},
                {0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0},
                {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0},
                {1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,0,0,0},
        };
    }

    public void dibujar (Jugador jugador) {
        for (int i = 0; i < laberinto.length; i++) {
            for(int j = 0; j < laberinto[i].length; j++){
                if(elJugadorHaTomadoGema){
                    dibujarMaterial(parse(laberinto[i][j]));
                    if(estaElJugador(jugador, i, j)) System.out.println("X ");
                    continue;
                }

                if(estaElJugador(jugador, i, j)){
                    System.out.print("X ");

                    if(elJugadorHaPisadoLaGema(jugador)){
                        jugador.gemas++;
                    }

                    continue;
                }
                if(estaLaGema(i, j)){
                    dibujarMaterial(GEMA);
                    continue;
                }
                if(estaCercaDelPersonaje(jugador, i, j)){
                    dibujarMaterial(parse(laberinto[i][j]));
                }else{
                    dibujarMaterial(DESCONOCIDO);
                }
            }

            System.out.println();
        }

        elJugadorHaTomadoGema = false;
    }

    private boolean elJugadorHaPisadoLaGema (Jugador jugador) {
        return jugador.x == gemaX && jugador.y == gemaY;
    }

    private boolean estaElJugador (Jugador jugador, int x, int y) {
        return jugador.x == x && jugador.y == y;
    }

    private boolean estaLaGema (int x, int y) {
        return x == gemaX && y == gemaY;
    }

    private boolean estaCercaDelPersonaje (Jugador jugador, int x, int y) {
        return Math.abs(x - jugador.x) <= 2 && Math.abs(y - jugador.y) <=2;
    }

    private void dibujarMaterial(Material material) {
        if(material == NADA) System.out.print("  ");
        if(material == PARED) System.out.print("[]");
        if(material == DESCONOCIDO) System.out.print("? ");
        if(material == GEMA) System.out.print("@ "); //mosaico
    }

    public enum Material {
        NADA(0),
        PARED(1),
        DESCONOCIDO(2),
        GEMA(3);

        public static Material parse (int tipo) {
            return Arrays.stream(Material.values())
                    .filter(mat -> mat.tipo == tipo)
                    .findAny()
                    .get();
        }

        private int tipo;

        Material(int tipo) {
            this.tipo = tipo;
        }
    }
}
