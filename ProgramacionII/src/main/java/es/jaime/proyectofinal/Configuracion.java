package es.jaime.proyectofinal;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase donde guardaremos todos los datos del usuario que introduzca:
 * color, si hay relleno o no, texto a escribir
 */
public final class Configuracion {
    private final Map<String, Object> configMap;

    public Configuracion() {
        this.configMap = new HashMap<>();
        setColor(Color.BLACK);
        setFuente(new Font("TimesRoman", Font.PLAIN, 10));
        setRelleno(false);
    }

    public Color getColor() {
        return (Color) configMap.get("color");
    }

    public boolean getRelleno() {
        return (boolean) configMap.get("relleno");
    }

    public String getTipoFigura() {
        return (String) configMap.get("figura");
    }

    public String getTexto() {
        return (String) configMap.get("texto");
    }

    public int getX1() {
        return (int) configMap.get("x1");
    }

    public int getY1() {
        return (int) configMap.get("y1");
    }

    public int getX2() {
        return (int) configMap.get("x2");
    }

    public int getY2() {
        return (int) configMap.get("y2");
    }

    public Font getFuente() {
        return (Font) configMap.get("fuente");
    }

    public boolean figuraNoSeleccionada() {
        return configMap.get("figura") == null;
    }

    public void setColor(Color color) {
        configMap.put("color", color);
    }

    public void setRelleno(boolean relleno) {
        configMap.put("relleno", relleno);
    }

    public void setTipoFigura(String tipoFigura) {
        configMap.put("figura", tipoFigura);
    }

    public void setPunto1(int x1, int y1) {
        configMap.put("x1", x1);
        configMap.put("y1", y1);
    }

    public void setPunto2(int x2, int y2) {
        configMap.put("x2", x2);
        configMap.put("y2", y2);
    }

    public void setTexto(String texto) {
        configMap.put("texto", texto);
    }

    public void setFuente(Font fuente) {
        configMap.put("fuente", fuente);
    }
}
