package AlgoritmiaII.recursion;

import java.io.IOException;

public class SumRange{
    public long sumRange(long start, long end){
        return sumRange(start, end,0);
    }

    public long sumRange(long start, long end, long acumulator){
        return start != end ? sumRange(start + 1, end, acumulator + start) : acumulator + start;
    }

    public static void main(String[] args) throws IOException {
        SumRange sumRange = new SumRange();

        System.out.println(sumRange.sumRange(2,5));
        System.out.println(sumRange.sumRange(0,0));
        System.out.println(sumRange.sumRange(-5,-3));
    }
}
