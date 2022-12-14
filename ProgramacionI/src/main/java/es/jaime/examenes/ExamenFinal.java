package es.jaime.examenes;


import java.util.Scanner;

/**
 * - Respues al reto 3)
 * Si tuviesemos solo 5 enfermos, la forma mas optima para reducir la velocidad de contagios seria
 * ponerlos todos juntos en una esquina del mapa. De esta forma, tendran el mas minimo contacto con las
 * personas sanas.
 *
 * Ademas para disminuir aun mas la velocidad de contagio. Todos los usuarios sanos deberian llevar mascarilla.
 *
 * He dejado configurado el mapa de la forma optima para evitar contagios
 */

public class ExamenFinal {
    private static final Scanner intput = new Scanner(System.in);
    private static boolean activo = true;

    private static final int total = 10 * 10;
    private static int sanos = (10 * 10) - 1;
    private static int enfermos = 1;
    private static int contagiados = 0;


    public static void main(String[] args) {
        while (activo) {
            imprimirTodo();
            escucharInputUsuario();
        }
    }

    private static void imprimirTodo() {
        contagiados = 0;

        saltoLinea();
        imprimirMapa();
        imprirInformacion();
    }

    private static void imprirInformacion () {
        System.out.println("Total: " + total + " / Sanos: " + sanos + " / Enfermos: " + enfermos + " / Contagiados: " + contagiados);
    }

    private static void imprimirMapa () {
        for(int fila = 0; fila < mapa.length; fila++){

            for(int columna = 0; columna < mapa[fila].length; columna++){
                relizarParseo(mapa[fila][columna]);

                if(mapa[fila][columna] == ID_ENFERMO)
                    realizarPosibleContagio(fila, columna);
            }

            saltoLinea();
        }
    }

    private static void relizarParseo (int valor) {
        if(valor == ID_SANO) print("'.' ");
        if(valor == ID_ENFERMO) print("[*] ");
        if(valor == ID_MASCARILLA) print("'=' ");
    }

    private static void realizarPosibleContagio (int filas, int columnas) {
        //El numero de "vecinos" de una posicion del array variara dependiendo si esta en los bordes
        // en las esquinas o ninguno de las dos.

        boolean estaEnElBordeSuperior = filas - 1 < 0;
        boolean estaEnElBordeIzquierdo = columnas - 1 < 0;
        boolean estaEnElBordeInferior = filas + 1 >= mapa.length;
        boolean estaEnElBordeDerecho = columnas + 1 >= mapa[0].length;

        if(estaEnElBordeSuperior && estaEnElBordeIzquierdo) { //Esquina superior izquierda
            realizarContagio(0, 1);
            realizarContagio(1, 0);
            realizarContagio(1, 1);

        }else if (estaEnElBordeInferior && estaEnElBordeIzquierdo) { //esquina inferior izquierda
            realizarContagio(filas - 1, columnas);
            realizarContagio(filas, columnas + 1);
            realizarContagio(filas - 1, columnas + 1);

        }else if (estaEnElBordeDerecho && estaEnElBordeSuperior){ //esquina superior derecha
            realizarContagio(filas, columnas - 1);
            realizarContagio(filas + 1, columnas);
            realizarContagio(filas + 1, columnas - 1);

        }else if (estaEnElBordeDerecho && estaEnElBordeInferior) { //esquina inferior derecha
            realizarContagio(filas, columnas - 1);
            realizarContagio(filas - 1, columnas);
            realizarContagio(filas - 1, columnas - 1);

        }else if (estaEnElBordeSuperior) {
            realizarContagio(filas, columnas + 1);
            realizarContagio(filas, columnas -1);
            realizarContagio(filas + 1, columnas );
            realizarContagio(filas + 1, columnas + 1);
            realizarContagio(filas + 1, columnas - 1);

        }else if (estaEnElBordeInferior) {
            realizarContagio(filas, columnas - 1);
            realizarContagio(filas, columnas + 1);
            realizarContagio(filas - 1, columnas);
            realizarContagio(filas - 1, columnas - 1);
            realizarContagio(filas - 1, columnas + 1);

        }else if (estaEnElBordeIzquierdo) {
            realizarContagio(filas + 1, columnas);
            realizarContagio(filas - 1, columnas);
            realizarContagio(filas, columnas + 1);
            realizarContagio(filas + 1, columnas + 1);
            realizarContagio(filas - 1, columnas + 1);

        }else if (estaEnElBordeDerecho) {
            realizarContagio(filas + 1, columnas);
            realizarContagio(filas - 1, columnas);
            realizarContagio(filas, columnas - 1);
            realizarContagio(filas - 1, columnas - 1);
            realizarContagio(filas + 1, columnas - 1);

        }else{ //No esta en ningun borde y ninguna esquina:
            realizarContagio(filas - 1, columnas);
            realizarContagio(filas + 1, columnas);
            realizarContagio(filas, columnas + 1);
            realizarContagio(filas, columnas - 1);
            realizarContagio(filas - 1, columnas - 1);
            realizarContagio(filas - 1, columnas + 1);
            realizarContagio(filas + 1, columnas - 1);
            realizarContagio(filas + 1, columnas + 1);
        }
    }

    private static void realizarContagio (int fila, int columna) {
        boolean seContagia = false;

        if(mapa[fila][columna] == ID_SANO){
            seContagia = Math.random() <= 0.15;

        }else if(mapa[fila][columna] == ID_MASCARILLA){
            seContagia = Math.random() <= 0.02;
        }

        if(seContagia) {
            mapa[fila][columna] = ID_ENFERMO;
            contagiados++;
            enfermos++;
            sanos--;
        }
    }

    private static void escucharInputUsuario () {
        String tecla = intput.next();

        if(tecla.equalsIgnoreCase("e"))
            activo = false;
    }

    private static void print (String text) {
        System.out.print(text);
    }

    private static void println (String text) {
        System.out.println(text);
    }

    private static void saltoLinea () {
        System.out.println();
    }

    private static final int[][] mapa = new int[][]{
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1, 1, 2, 2, 2, 2, 2, 2, 2, 2},
            {1, 1, 1, 2, 2, 2, 2, 2, 2, 2},
    };

    private static final int ID_SANO = 0;
    private static final int ID_ENFERMO = 1;
    private static final int ID_MASCARILLA = 2;
}
