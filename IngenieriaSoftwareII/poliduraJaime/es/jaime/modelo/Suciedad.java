package es.jaime.modelo;

public final class Suciedad {
    public static final int MINIMO_NIVEL = 0;
    public static final int MAXIMO_NIVEL = 3;

    private int nivel;

    public Suciedad(int nivelInicial) {
        this.nivel = nivelInicial;
    }

    public void ensuciar() {
        if(this.nivel < MAXIMO_NIVEL) {
            this.nivel++;
        }
    }

    public int getNivel() {
        return this.nivel;
    }
}
