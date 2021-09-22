package ProgramacionI;

public class Marco {
    public static void main(String[] args) {
        double velocidadMarco = random(10, 15);
        double timepoMarco = random(10, 8);
        double espacioMadre = 100;

        double espacioMarco = velocidadMarco * timepoMarco;

        while (espacioMadre > espacioMarco) {
            double probabilidadClima = Math.random();

            if (probabilidadClima < 0.1) {
                velocidadMarco = velocidadMarco * 0.25; // Lluvia fuerte
            }else if (probabilidadClima < 0.4) {
                velocidadMarco = velocidadMarco * 0.75; //Lluvia normal
            }

            double probabilidadMonoEscapado = Math.random();

            if(probabilidadMonoEscapado < 0.15) {
                velocidadMarco = velocidadMarco * 0.9;
            }

            espacioMarco = velocidadMarco * timepoMarco;

            if ((espacioMadre - espacioMarco) < 50) {
                double prob = Math.random();

                if (prob <= 0.5) {

                }
            }
        }



    }

    private static double random (double max, double min) {
        return Math.random() * (max - min + 1) + min;
    }
}
