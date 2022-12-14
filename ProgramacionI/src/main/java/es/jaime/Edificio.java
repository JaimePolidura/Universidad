package es.jaime;

public class Edificio {
    public static void main(String[] args) {
        int numeroPlantas = (int) ((Math.random() * 19) + 1);

        System.out.println("+--------------+"); //18
        for(int i = 0; i < numeroPlantas; i++){
            int numeroVentanas = (int) ((Math.random() * 3) + 1);

            StringBuilder planta = new StringBuilder("| ");

            for(int j = 0; j < numeroVentanas; j++) {
                boolean abierto = Math.random() > 0.5;

                if(abierto) {
                    planta.append("[º]");
                }else {
                    planta.append("[*]");
                }
            }

            int espaciosAImprimir = 18 - (planta.length() + 2);

            for (int k = 0; k < espaciosAImprimir - 2; k++) {
                planta.append(" ");
            }

            planta.append(" |");

            System.out.println(planta.toString());
        }

        System.out.println("================");
    }
}
