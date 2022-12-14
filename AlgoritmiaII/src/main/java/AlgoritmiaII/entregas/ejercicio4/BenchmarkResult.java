package AlgoritmiaII.entregas.ejercicio4;

import java.util.Arrays;

public class BenchmarkResult {
    final long[] times;

    public BenchmarkResult(long[] times) {
        this.times = times;
    }

    public int getTimes(){
        return times.length;
    }

    public long getMax(){
        return Arrays.stream(this.times).max().orElse(-1);
    }

    public long getMin(){
        return Arrays.stream(this.times).min().orElse(-1);
    }

    public double getAverage(){
        return 1.0d * getTotalTime()/ getTimes();
    }

    public long getTotalTime(){
        return Arrays.stream(this.times).sum();
    }

    @Override
    public String toString() {
        return "BenchmarkResult{" +
                "ave=" + getAverage() +
                '}';
    }
}
