package AlgoritmiaII.proyectofinal.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public final class ToDimensionalListCollector<T> implements Collector<List<T>, List<List<T>>, List<List<T>>> {
    private final int itemsPerList;

    public ToDimensionalListCollector(int itemsPerList) {
        this.itemsPerList = itemsPerList;
    }

    @Override
    public Supplier<List<List<T>>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<List<T>>, List<T>> accumulator() {
        return (list, toCombine) -> list.add(toCombine);
    }

    @Override
    public BinaryOperator<List<List<T>>> combiner() {
        return null;
    }

    @Override
    public Function<List<List<T>>, List<List<T>>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
