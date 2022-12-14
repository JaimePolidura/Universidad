package AlgoritmiaII.entregas.ejercicio2;

public class BenchmarkResult {
    private final int times;
    private final long totalTime;
    private final double averageTime;
    private final long minTime;
    private final long maxTime;

    public BenchmarkResult(long[] times) {
        this.times = times.length;
        this.totalTime = computeTotalTime(times);
        this.averageTime = computeAverageTime(times);
        this.minTime = computeMinTime(times);
        this.maxTime = computeMaxTime(times);
    }

    private static long computeTotalTime(long[] times) {
        long totalTime = 0;
        for (long time : times) {
            totalTime += time;
        }
        return totalTime;
    }

    private static double computeAverageTime(long[] times) {
        return computeTotalTime(times) * 1.0 / times.length;
    }

    private static long computeMinTime(long[] times) {
        long minTime = Long.MAX_VALUE;
        for (long time : times) {
            if (time < minTime) {
                minTime = time;
            }
        }
        return minTime;
    }

    private static long computeMaxTime(long[] times) {
        long maxTime = Long.MIN_VALUE;
        for (long time : times) {
            if (time > maxTime) {
                maxTime = time;
            }
        }
        return maxTime;
    }

    public int getTimes() {
        return times;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public double getAverageTime() {
        return averageTime;
    }

    public long getMinTime() {
        return minTime;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public String toString() {
        return "Ran " + String.valueOf(getTimes()) + " times. "
                + "Total: " + String.valueOf(getTotalTime())
                + ", Average: " + String.valueOf(getAverageTime())
                + ", Min: " + String.valueOf(getMinTime())
                + ", Max: " + String.valueOf(getMaxTime())
                + " ms.";
    }
}
