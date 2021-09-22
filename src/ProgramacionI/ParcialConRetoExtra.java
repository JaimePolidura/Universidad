package ProgramacionI;

public class ParcialConRetoExtra {

    public static void main(String[] args) {
        double profundidadCaracol = Math.random() * (20 - 10 + 1) + 10;

        boolean sigueEnElPozo = true;
        boolean esDeDia = false;

        int diasTranscurridos = 0;
        int maximoMetrosPuedeSubir = 4;
        int metrosInundados = 1;

        while (sigueEnElPozo) {
            boolean lluviaDebil = false;
            boolean lluviaFuerte = false;
            double metrosSubidosEnUnDia = 0;
            diasTranscurridos++;

            /** Dependiendo del clima subimos los metros inundados */
            double probabilidadLluvia = Math.random();
            if(probabilidadLluvia <= 0.05){ //Lluvia fuerte
                metrosInundados = metrosInundados + 5;
                lluviaFuerte = true;
            }else if (probabilidadLluvia <= 0.1) { //Lluvia debil
                metrosInundados = metrosInundados + 2;
                lluviaDebil = true;
            }

            /** Vemos cuantos dias lleva el caracol y dependiendo del numero podra subir como maximo n metros*/
            if(diasTranscurridos >= 10){
                maximoMetrosPuedeSubir = 3;
            }else if (diasTranscurridos >= 20){
                maximoMetrosPuedeSubir = 2;
            }

            /** Nos encargamos de poner cuantos metros sube el caracol dependiendo si es de noche o de dia*/
            if(esDeDia){
                metrosSubidosEnUnDia = metrosSubidosEnUnDia + Math.random() * maximoMetrosPuedeSubir + 1;
                profundidadCaracol = profundidadCaracol - metrosSubidosEnUnDia;
                esDeDia = false;
            }else{
                metrosSubidosEnUnDia = metrosSubidosEnUnDia + Math.random() * 2;
                profundidadCaracol = profundidadCaracol - metrosSubidosEnUnDia;
                esDeDia = true;
            }

            /** Nos encargamos de ver si ha aparcado un coche o no*/
            boolean haAparcadoUnCoche = Math.random() <= 0.65; //Probabilidad de que aparque un coche es del 35%
            if(haAparcadoUnCoche){
                profundidadCaracol = profundidadCaracol + 2;
                metrosSubidosEnUnDia = metrosSubidosEnUnDia - 2;
            }

            /** Nos encargamos de comprobar la profundidad del caracol por si ha salido del pozo o se ha undido y sino dibujomos el caracol y el pozo*/
            if (profundidadCaracol > 20) {
                System.out.println("El caracol ha muerto");
                sigueEnElPozo = false;
            }else if (profundidadCaracol < 0) {
                System.out.println("El caracol ha salido del pozo en el dia " + diasTranscurridos);
                sigueEnElPozo = false;
            }else if (profundidadCaracol >= (20 - metrosInundados)) {
                System.out.println("El caracol le ha pillado el agua en el dia " + diasTranscurridos + " a una profundidad de " + -profundidadCaracol + " m");
                sigueEnElPozo = false;
            }else if (diasTranscurridos >= 50) {
                System.out.println("El caracol ha muerto por cansancio en el dia 50 a una profundidad de " + -profundidadCaracol + " m");
                sigueEnElPozo = false;
            }else{ //EL caracol sigue vivo asi que hacemos el dibujo en pantalla

                /** Parte del codigo encargada de construir el texto que se mostrara en la pantalla **/
                System.out.println("    ");
                System.out.println("Dia ["+diasTranscurridos+"] / Cambio de metros ["+metrosSubidosEnUnDia+"] / Altura ["+-profundidadCaracol+"]");
                if(lluviaDebil) System.out.println("Lluvia debil"); //Cuando un if solo tiene un linea se puede omitir las llaves. Usado para simplificar el codigo
                if(lluviaFuerte) System.out.println("Lluvia fuerte");
                if(haAparcadoUnCoche) System.out.println("Ha aparcado un coche");
                System.out.println("    ");

                if(haAparcadoUnCoche) System.out.println("      o[|||]o  ");
                for(int i = 0; i < (20 - metrosInundados); i++){
                    if(i == (int) profundidadCaracol) { //dibujamos la parte del pozo no inundada por agua
                        System.out.println("[]:. _(O)_/'. :. [] _ __ " + i);
                    }else {
                        System.out.println("[]:. :. :. :. :. [] _ __ " + i);
                    }
                }

                for(int i = 0; i < metrosInundados; i++){ //dibujamos la parte del pozo inundada por agua
                    System.out.println("[]~~~~~~~~~~~~~~~[]");
                }
                System.out.println("[][][][][][][][][]");
                System.out.println("    ");
            }
        }
    }
}
