package es.jaime.proyectofinal.figuras;

public enum TipoFiguras {
    TEXTO("Texto"),
    RECTANGULO("Rectangulo"),
    ELIPSE("Elipse");

    public final String nombre;

    TipoFiguras(String nombre) {
        this.nombre = nombre;
    }
}
