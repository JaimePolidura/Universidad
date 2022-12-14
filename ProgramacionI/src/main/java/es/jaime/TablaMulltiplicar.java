package es.jaime;

import java.util.Scanner;

public class TablaMulltiplicar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce un numero: ");
        int numero = scanner.nextInt();

        for(int i = 1; i < 11; i++){
            System.out.println(numero + " x " + i + " = " + (numero * i));
        }
    }
}
