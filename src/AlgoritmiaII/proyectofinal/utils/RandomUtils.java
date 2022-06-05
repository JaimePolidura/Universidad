package AlgoritmiaII.proyectofinal.utils;

public final class RandomUtils {
    public static boolean matchesPossibility(double possibility){
        return ( possibility / 100) >= Math.random();
    }
}
