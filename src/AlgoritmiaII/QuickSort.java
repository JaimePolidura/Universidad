package AlgoritmiaII;

import java.util.Arrays;

public final class QuickSort {
    public static void main(String[] args) {
        int a[] = new int[]{2, 3, 2, 5, 1};
        int size = a.length;
        boolean swaped = true;

        while (swaped){
            swaped = false;
            for(int i = 0; i < size - 1; i++){
                int actual = a[i];
                int next = a[i + 1];

                if(actual > next){
                    a[i] = next;
                    a[i + 1] = actual;
                    swaped = true;
                }
            }
            size--;
        }

        System.out.println(Arrays.toString(a));
    }
}
