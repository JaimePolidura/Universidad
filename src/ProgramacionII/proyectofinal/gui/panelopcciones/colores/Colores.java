package ProgramacionII.proyectofinal.gui.panelopcciones.colores;

import java.awt.*;
import java.util.Arrays;

/**
 * Aunque esto no lo hemos dado nos ayuda a evitar poner muchos ifs en nuestro programa.
 * Si quiesiesemos añadir un nuevo color tann solo tendriamos que añadir un nuevo elemento
 * del enum, con sus propiedades
 */
public enum Colores {
    NARANJA("Naranja", Color.ORANGE),
    AMARILLO("Amarillo", Color.YELLOW),
    NEGRO("Negro", Color.BLACK),
    MARRON("Marron", new Color(139,69,19));

    public final Color color;
    public final String nombre;

    Colores(String nombre, Color color) {
        this.nombre = nombre;
        this.color = color;
    }

    public static Color getColorDeNombreDeLaLista(String nombre) {
        return Arrays.stream(Colores.values())
                .filter(color -> color.nombre.equalsIgnoreCase(nombre))
                .findAny()
                .get().color;
    }
}
