package es.laberinto.utils;

public record Vector(int x, int y) {
    public Vector aumentarSoloEnDireccion(Direccion direccion, int aumentar) {
        return new Vector(x + direccion.cambioX() * aumentar, y + direccion.cambioY() * aumentar);
    }

    public Vector aumentarEn(double aumentar) {
        return new Vector((int) (x * aumentar), (int) (y * aumentar));
    }

    public static Vector crear(int x, int y) {
        return new Vector(x, y);
    }
}
