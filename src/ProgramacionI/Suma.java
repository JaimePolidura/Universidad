package ProgramacionI;

public class Suma {
    public static void main(String[] args) {
        double tiempo1 = System.nanoTime();
        double num = getDigit(2123, 2, 4);
        tiempo1 = System.nanoTime() - tiempo1;

        double tiempo2 = System.nanoTime();
        double num2 = Double.parseDouble(String.valueOf("2123".toCharArray()[2]));
        tiempo2 = System.nanoTime() - tiempo2;



        System.out.println("String   " + tiempo2 / 1000000);
        System.out.println("Numerico " + tiempo1 / 1000000);
        System.out.println(( (tiempo2/tiempo1)) + "x");
    }

    public static double getDigit(double number, double index, double lengthOfTheNumber){
        double currentIndex = lengthOfTheNumber - 1;
        double factor = Math.pow(10, currentIndex);
        double result = -1;

        while (factor % 10 == 0) {
            result = number / factor;
            number = number % factor;

            if(index == currentIndex){
                return result;
            }

            currentIndex--;
            factor = factor / 10;

            if(factor % 10 != 0){
                return number;
            }
        }

        return result;
    }
}
