package ProgramacionII.lienzo;

public class Complejo {
    private int real;
    private int imaginario;

    public Complejo(int real, int imaginario) {
        this.real = real;
        this.imaginario = imaginario;
    }

    public Complejo () {
        this(0, 0);
    }

    public int getReal() {
        return real;
    }

    public int getImaginario() {
        return imaginario;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public void setImaginario(int imaginario) {
        this.imaginario = imaginario;
    }
}
