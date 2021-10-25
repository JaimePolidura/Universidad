package AlgoritmiaI.entregas.ejercicio1.ejercicio1;

public final class Ejercicio1 {

    public static String removeEs(String textWithE) {
        StringBuilder textWithoutE = new StringBuilder();

        for(int i = 0; i < textWithE.length(); i++){
            char actualChar = textWithE.charAt(i);

            if(actualChar != 'E' && actualChar != 'e'){
                textWithoutE.append(textWithE.charAt(i));
            }
        }

        return textWithoutE.toString();
    }
}
