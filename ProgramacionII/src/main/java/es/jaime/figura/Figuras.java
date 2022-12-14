package es.jaime.figura;

public class Figuras {
    public static void main(String[] args) {
        Figura figura = new Circulo(10);
        System.out.printf("El perimetro es %s y el area es %s", figura.getPerimetro(), figura.getArea());
    }
}
