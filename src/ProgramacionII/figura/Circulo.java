package ProgramacionII.figura;

public class Circulo extends Figura{
    private final double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    @Override
    public double getPerimetro() {
        return 2 * this.radio * Math.PI;
    }

    @Override
    public double getArea() {
        return Math.pow(this.radio, 2) * Math.PI;
    }
}
