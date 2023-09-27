package es.jaime.modelo;

public record Posicion(int x, int y) {
    public Posicion nuevaPosicionAPartirDe(Direccion direccion) {
        return new Posicion(x + direccion.cambioX, y + direccion.cambioY);
    }

    public static Posicion aleatorio(int minX, int maxX, int minY, int maxY) {
        int x = (int) (Math.random() * maxX) + minX;
        int y = (int) (Math.random() * maxY) + minY;

        return new Posicion(x, y);
    }
}
