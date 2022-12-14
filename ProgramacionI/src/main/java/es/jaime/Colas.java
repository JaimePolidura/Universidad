package es.jaime;


public class Colas {
    public static void main(String[] args) {
        int personasEnLaCola = 1;

        int caja1Items = 0;
        int caja2Items = 0;
        int caja3Items = 0;
        int caja4Items = 0;

        for (int i = 1; i < 720; i = i + 1) {

            if(personasEnLaCola > 0){
                personasEnLaCola--;

                if(caja1Items == 0) {caja1Items = random(5, 15); }
                else if(caja2Items == 0) {caja2Items = random(5, 15);}
                else if(caja3Items == 0) {caja3Items = random(5, 15);}
                else if(caja4Items == 0) {caja4Items = random(5, 15);}
                else {personasEnLaCola++;}
            }

            if(caja1Items > 0) {caja1Items--;}
            if(caja2Items > 0) {caja2Items--;}
            if(caja3Items > 0) {caja3Items--;}
            if(caja4Items > 0) {caja4Items--;}

            System.out.println("------------------------------");
            if(random(0, 100) <= random(0, 40)){
                personasEnLaCola++;
                System.out.println("Ha llegado una persona a la cola");
            }

            System.out.println("Minuto: " + i + " 1["+caja1Items+"] 2["+caja2Items+"] 3["+caja3Items+"] 4["+caja4Items+"] Cola: " + personasEnLaCola);
            System.out.println("------------------------------");
        }
    }

    private static int random (int from, int to) {
        return (int) ((Math.random() * to) + from);
    }
}
