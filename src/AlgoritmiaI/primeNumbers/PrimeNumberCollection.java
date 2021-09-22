package AlgoritmiaI.primeNumbers;

public final class PrimeNumberCollection {
    public final int[] primeNumbers;
    public final int maxValue;
    public final int minValue;

    public PrimeNumberCollection(int[] primeNumbers, int maxValue, int min) {
        this.primeNumbers = primeNumbers;
        this.maxValue = maxValue;
        this.minValue = min;
    }
}
