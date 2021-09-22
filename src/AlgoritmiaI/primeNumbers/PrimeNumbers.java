package AlgoritmiaI.primeNumbers;

public final class PrimeNumbers {
    public static PrimeNumberCollection getPrimeCollection(int[] values){
        int maxValue = getMaxValue(values);
        int minValue = getMinValue(values);
        int[] primeNumbers = getPrimes(values);

        return new PrimeNumberCollection(primeNumbers, maxValue, minValue);
    }

    private static int getMinValue(int[] integers) {
        int minValue = integers[0];

        for (int i = 0; i < integers.length; i++) {
            if(integers[i] < minValue){
                minValue = integers[i];
            }
        }

        return minValue;
    }

    private static int getMaxValue(int[] integers) {
        int maxValue = integers[0];

        for (int i = 0; i < integers.length; i++) {
            if(integers[i] > maxValue){
                maxValue = integers[i];
            }
        }

        return maxValue;
    }

    private static int[] getPrimes(int[] values) {
        int[] primeNumbers = new int[values.length];

        for(int i = 0; i < values.length; i++){
            int numberToCheckIfPrime = values[i];

            if(isPrime(numberToCheckIfPrime)){
                primeNumbers[i] = numberToCheckIfPrime;
            }
        }

        return primeNumbers;
    }

    private static boolean isPrime(int num) {
        boolean isPrime = true;

        for(int i = 2; i < num; i++){
            if(num % i == 0){
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }
}
