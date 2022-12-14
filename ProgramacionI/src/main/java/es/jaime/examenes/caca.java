package es.jaime.examenes;

public class caca {
    private static void realizarPosibleContagio (int misFilas, int misColumnas, int[][] array) {

        if(misFilas - 1 < 0 && misColumnas - 1 < 0) { //Esquina superior izquierda

        }else if (misFilas + 1 >= array.length && misColumnas - 1 < 0) { //esquina inferior izquierda

        }else if (misColumnas + 1 >= array[0].length && misFilas - 1 < 0){ //esquina superior derecha

        }else if (misColumnas + 1 >= array[0].length && misFilas + 1 >= array.length) { //esquina inferior derecha

        }else if (misFilas - 1 < 0) { //borde superior

        }else if (misFilas + 1 >= array[0].length) { //borde inferior

        }else if (misColumnas - 1 < 0) { //borde izquierdo

        }else if (misColumnas + 1 <= array[0].length) { //borde derecho

        }else{

        }
    }
}
