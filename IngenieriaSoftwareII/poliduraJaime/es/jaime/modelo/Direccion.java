package es.jaime.modelo;

public enum Direccion {
    ARRIBA(0, 1),
    ABAJO(0, -1),
    IZQUIERDA(-1, 0),
    DERECHA(1, 0);

    public final int cambioX;
    public final int cambioY;

    Direccion(int cambioX, int cambioY) {
        this.cambioX = cambioX;
        this.cambioY = cambioY;
    }

    public static Direccion nuevaDireccionAleatoria() {
        double valorAleatorio = Math.random();

        return valorAleatorio > 0.5 ?
                (valorAleatorio > 0.75 ? Direccion.ARRIBA : Direccion.ABAJO) :
                (valorAleatorio > 0.25 ? Direccion.IZQUIERDA : Direccion.DERECHA);
    }
}
