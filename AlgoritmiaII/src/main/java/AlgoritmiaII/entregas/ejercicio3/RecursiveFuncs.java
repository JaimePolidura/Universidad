package AlgoritmiaII.entregas.ejercicio3;

import java.io.IOException;

public final class RecursiveFuncs{
    public static int invertDigits(int i) {
        String text = String.valueOf(i);

        String result = text.startsWith("-") ?
                "-" + invertDigitsRecursive(text.substring(1),text.substring(1).length()-1,"") :
                invertDigitsRecursive(text, text.length()-1,"");

        return Integer.parseInt(result);
    }

    private static String invertDigitsRecursive(String original, int actualIndex, String result) {
        if (actualIndex == -1)
            return result;

        String actualText = result + original.charAt(actualIndex);

        return invertDigitsRecursive(original, --actualIndex, actualText);
    }

    public static int sumArrayElements(int[] ints) {
        return sumArraysRecursive(ints,0);
    }

    private static int sumArraysRecursive(int[] ints, int actualIndex){
        if(actualIndex == ints.length)
            return 0;

        return ints[actualIndex] + sumArraysRecursive(ints, ++actualIndex);
    }

    public static int sumMatrixElements(int[][] ints) {
        int actualSum = 0;

        for (int i = 0; i < ints.length; i++) {
            actualSum += sumMatrixRow(ints[i]);
        }

        return actualSum;
    }

    private static int sumMatrixRow(int[] matrix){
        return sumArrayElements(matrix);
    }

    public static String request(TestRequestable requestable, int maxTimes) {
       return requestRecursive(requestable, maxTimes, 0);
    }

    private static String requestRecursive(TestRequestable requestable, int maxTimes, int actualTimes){
        try {
            if (actualTimes + 1 > maxTimes)
                return null;

            return requestable.request();
        } catch (IOException e) {
            return requestRecursive(requestable, maxTimes, actualTimes + 1);
        }
    }
}
