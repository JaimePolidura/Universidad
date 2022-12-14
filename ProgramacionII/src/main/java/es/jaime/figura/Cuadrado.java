package es.jaime.figura;

public class Cuadrado extends Figura{
    private final double base;

    public Cuadrado(double base) {
        this.base = base;
    }

    public double getBase() {
        return base;
    }

    @Override
    public double getPerimetro() {
        return base * 4;
    }

    @Override
    public double getArea() {
        return Math.pow(base, 2);
    }
}
