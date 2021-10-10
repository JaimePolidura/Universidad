package AlgoritmiaI.practica1.ejercicio2;

public final class Sorter {
    public static int[] bubbleSort (int[] array) {
        int arrayLength = array.length;
        int aux = 0;

        for(int i = 0; i < arrayLength; i++){
            for(int j = 1; j < (arrayLength - i); j++){
                if(array[j - 1] > array[j]) {
                    aux = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = aux;
                }
            }
        }

        return array;
    }
}
