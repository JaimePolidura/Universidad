package es.jaime.modelo;

public final class Baldosa {
    private final Suciedad suciedad;

    public Baldosa(int valorInicial) {
        this.suciedad = new Suciedad(valorInicial);
    }

    public void ensuciar() {
        this.suciedad.ensuciar();
    }

    public Suciedad getSuciedad() {
        return this.suciedad;
    }
}
