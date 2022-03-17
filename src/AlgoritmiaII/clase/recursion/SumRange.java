package AlgoritmiaII.clase.recursion;

public class SumRange {
    public long sumRange(long start, long end){
        return sumRange(start, end,0);
    }

    public long sumRange(long start, long end, long acumulator){
        return start == end ? acumulator + start : sumRange(start + 1, end, acumulator + start);
    }

    public static void main(String[] args) {
        SumRange sumRange = new SumRange();

        System.out.println(sumRange.sumRange(2,5));
    }
}
