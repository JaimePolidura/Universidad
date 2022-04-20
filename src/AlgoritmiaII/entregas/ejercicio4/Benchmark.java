package AlgoritmiaII.entregas.ejercicio4;

public class Benchmark {

    public static BenchmarkResult run(Runnable runnable, int numRepeats){
        long[] times = new long[numRepeats];

        for(int i = 0; i < times.length; ++i){
            final long before = System.currentTimeMillis();
            runnable.run();
            final long after = System.currentTimeMillis();

            times[i] = after - before;
        }

        return new BenchmarkResult(times);
    }
}
