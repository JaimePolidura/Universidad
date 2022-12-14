package es.jaime.lienzo;

import java.awt.*;

public final class Texto implements Figura{
    private final int x, y;
    private final String texto;
    private final Color color;

    public Texto(int x, int y, String texto, Color color) {
        this.x = x;
        this.y = y;
        this.texto = texto;
        this.color = color;
    }

    @Override
    public void pintar(Graphics g) {
        g.setColor(color);
        g.drawString(texto, x, y);
    }
}
