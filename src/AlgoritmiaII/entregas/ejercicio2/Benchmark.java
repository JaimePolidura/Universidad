package AlgoritmiaII.entregas.ejercicio2;

public class Benchmark {
    public static BenchmarkResult run(Runnable runnable, int numRepeats) {
        long[] times = new long[numRepeats];

        for (int i = 0; i < numRepeats; ++i) {
            final long before = System.currentTimeMillis();
            runnable.run();
            final long after = System.currentTimeMillis();
            times[i] = after - before;
        }
        return new BenchmarkResult(times);
    }
}
