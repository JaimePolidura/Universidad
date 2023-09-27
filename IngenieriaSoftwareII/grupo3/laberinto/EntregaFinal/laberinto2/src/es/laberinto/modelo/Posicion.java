package es.laberinto.modelo;

import java.util.Objects;

public record Posicion(int x, int y) {
    public Posicion nuevaPosicionAPartirDe(Vector vector) {
        return new Posicion(x + vector.x(), y + vector.y());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return x == posicion.x && y == posicion.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean mismaPosicion(Posicion posicion) {
        return posicion != null && posicion.x == this.x && posicion.y == this.y;
    }

    public static Posicion crear(int x, int y) {
        return new Posicion(x, y);
    }
}
