package es.laberinto.entidades;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import java.util.function.Predicate;

public final class ArgPredicateMatcher<T> implements ArgumentMatcher<T> {
    private final Predicate<T> predicate;

    public ArgPredicateMatcher(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean matches(T t) {
        return predicate.test(t);
    }

    public static <T> ArgPredicateMatcher<T> of(Predicate<T> matcher){
        return new ArgPredicateMatcher<>(matcher);
    }
}
