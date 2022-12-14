package es.jaime.parcial.figura;

public final class TrianguloIsosceles extends Triangulo{
    public TrianguloIsosceles(int lado) {
        super(lado, lado, lado);
    }

    @Override
    public void dibujarFigura() {
        System.out.println("Soy un triangulo isosceles");
    }
}
